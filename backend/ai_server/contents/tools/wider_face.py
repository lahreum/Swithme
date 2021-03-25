import time
import os
import numpy as np
import hashlib

from absl import app, flags, logging
from absl.flags import FLAGS
import tensorflow as tf
import lxml.etree
import tqdm

flags.DEFINE_string('data_dir', '../datasets/WIDER_Face',
                    'path to raw dataset')
flags.DEFINE_enum('split', 'train', [
                  'train', 'val'], 'specify train or val spit')
flags.DEFINE_string('output_file', '../data/wider_face_train.tfrecord', 'output dataset')
flags.DEFINE_string('classes', '../data/wider_face.names', 'classes file')

object_max_num = -1


def build_example(annotation, class_map):
    global object_max_num

    img_path = os.path.join(
        FLAGS.data_dir, 'JPEGImages', annotation['path'][2:])
    img_raw = open(img_path, 'rb').read()
    key = hashlib.sha256(img_raw).hexdigest()

    width = int(annotation['size']['width'])
    height = int(annotation['size']['height'])

    xmin = []
    ymin = []
    xmax = []
    ymax = []
    classes = []
    classes_text = []
    truncated = []
    views = []
    difficult_obj = []

    if 'object' in annotation:
        object_max_num = max(object_max_num, len(annotation['object']))

        for obj in annotation['object']:
            difficult = bool(int(obj['difficult']))
            difficult_obj.append(int(difficult))

            xmin_value = float(obj['bndbox']['xmin']) / width
            ymin_value = float(obj['bndbox']['ymin']) / height
            xmax_value = float(obj['bndbox']['xmax']) / width
            ymax_value = float(obj['bndbox']['ymax']) / height

            if xmin_value < 0.0:
                print(annotation['path'], float(obj['bndbox']['xmin']))
                xmin_value = 0.0
            elif xmin_value > 1.0:
                xmin_value = 1.0

            if ymin_value < 0.0:
                print(annotation['path'], float(obj['bndbox']['ymin']))
                ymin_value = 0.0
            elif ymin_value > 1.0:
                ymin_value = 1.0

            if xmax_value < 0.0:
                print(annotation['path'], float(obj['bndbox']['xmax']))
                xmax_value = 0.0
            elif xmax_value > 1.0:
                xmax_value = 1.0

            if ymax_value < 0.0:
                print(annotation['path'], float(obj['bndbox']['ymax']))
                ymax_value = 0.0
            elif ymax_value > 1.0:
                ymax_value = 1.0

            xmin.append(xmin_value)
            ymin.append(ymin_value)
            xmax.append(xmax_value)
            ymax.append(ymax_value)
            classes_text.append(obj['name'].encode('utf8'))
            classes.append(class_map[obj['name']])
            truncated.append(int(obj['truncated']))
            views.append(obj['pose'].encode('utf8'))

    example = tf.train.Example(features=tf.train.Features(feature={
        'image/height': tf.train.Feature(int64_list=tf.train.Int64List(value=[height])),
        'image/width': tf.train.Feature(int64_list=tf.train.Int64List(value=[width])),
        'image/filename': tf.train.Feature(bytes_list=tf.train.BytesList(value=[
            annotation['filename'].encode('utf8')])),
        'image/source_id': tf.train.Feature(bytes_list=tf.train.BytesList(value=[
            annotation['filename'].encode('utf8')])),
        'image/key/sha256': tf.train.Feature(bytes_list=tf.train.BytesList(value=[key.encode('utf8')])),
        'image/encoded': tf.train.Feature(bytes_list=tf.train.BytesList(value=[img_raw])),
        'image/format': tf.train.Feature(bytes_list=tf.train.BytesList(value=['jpeg'.encode('utf8')])),
        'image/object/bbox/xmin': tf.train.Feature(float_list=tf.train.FloatList(value=xmin)),
        'image/object/bbox/xmax': tf.train.Feature(float_list=tf.train.FloatList(value=xmax)),
        'image/object/bbox/ymin': tf.train.Feature(float_list=tf.train.FloatList(value=ymin)),
        'image/object/bbox/ymax': tf.train.Feature(float_list=tf.train.FloatList(value=ymax)),
        'image/object/class/text': tf.train.Feature(bytes_list=tf.train.BytesList(value=classes_text)),
        'image/object/class/label': tf.train.Feature(int64_list=tf.train.Int64List(value=classes)),
        'image/object/difficult': tf.train.Feature(int64_list=tf.train.Int64List(value=difficult_obj)),
        'image/object/truncated': tf.train.Feature(int64_list=tf.train.Int64List(value=truncated)),
        'image/object/view': tf.train.Feature(bytes_list=tf.train.BytesList(value=views)),
    }))
    return example


def parse_xml(xml):
    if not len(xml):
        return {xml.tag: xml.text}
    result = {}
    for child in xml:
        child_result = parse_xml(child)
        if child.tag != 'object':
            result[child.tag] = child_result[child.tag]
        else:
            if child.tag not in result:
                result[child.tag] = []
            result[child.tag].append(child_result[child.tag])
    return {xml.tag: result}


def main(_argv):
    class_map = {name: idx for idx, name in enumerate(
        open(FLAGS.classes).read().splitlines())}
    logging.info("Class mapping loaded: %s", class_map)

    writer = tf.io.TFRecordWriter(FLAGS.output_file)
    image_path = os.walk(os.path.join(FLAGS.data_dir, 'JPEGImages', 'WIDER_%s' % FLAGS.split, 'images'))
    image_list = []

    for dirpath, dirnames, filenames in image_path:
        for filename in filenames:
            image_list.append(filename.split('.')[0])

    logging.info("Image list loaded: %d", len(image_list))

    for name in tqdm.tqdm(image_list):
        annotation_xml = os.path.join(
            FLAGS.data_dir, 'Annotations', FLAGS.split, name + '.xml')
        annotation_xml = lxml.etree.fromstring(open(annotation_xml).read())
        annotation = parse_xml(annotation_xml)['annotation']
        tf_example = build_example(annotation, class_map)
        writer.write(tf_example.SerializeToString())

    writer.close()

    logging.info("Object Max Num: %d", object_max_num)
    logging.info("Done")


if __name__ == '__main__':
    app.run(main)
