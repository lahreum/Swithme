import json
import os

from tqdm import tqdm
from xmltodict import unparse

# BBOX_OFFSET: Switch between 0-based and 1-based bbox.
# The COCO dataset is in 0-based format, while the VOC dataset is 1-based.
# To keep 0-based, set it to 0. To convert to 1-based, set it to 1.
BBOX_OFFSET = 0

src_base = os.path.join("../datasets", "COCO_2014", "annotations")
dst_base = os.path.join("../datasets", "new")

dst_dirs = {x: os.path.join(dst_base, x) for x in ["Annotations", "ImageSets", "JPEGImages"]}
dst_dirs['ImageSets'] = os.path.join(dst_dirs['ImageSets'], "Main")
for k, d in dst_dirs.items():
    os.makedirs(d, exist_ok=True)


def base_dict(filename, width, height, depth=3):
    return {
        "annotation": {
            "filename": os.path.split(filename)[-1],
            "folder": "VOCCOCO", "segmented": "0", "owner": {"name": "unknown"},
            "source": {'database': "The COCO 2017 database", 'annotation': "COCO 2017", "image": "unknown"},
            "size": {'width': width, 'height': height, "depth": depth},
            "object": []
        }
    }


def base_object(size_info, name, bbox):
    x1, y1, w, h = bbox
    x2, y2 = x1 + w, y1 + h

    width = size_info['width']
    height = size_info['height']

    x1 = max(x1, 0) + BBOX_OFFSET
    y1 = max(y1, 0) + BBOX_OFFSET
    x2 = min(x2, width - 1) + BBOX_OFFSET
    y2 = min(y2, height - 1) + BBOX_OFFSET

    return {
        'name': name, 'pose': 'Unspecified', 'truncated': '0', 'difficult': '0',
        'bndbox': {'xmin': x1, 'ymin': y1, 'xmax': x2, 'ymax': y2}
    }


sets = {
    "train": os.path.join(src_base, "instances_train2014.json"),
    "val": os.path.join(src_base, "instances_val2014.json"),
}

cate = {x['id']: x['name'] for x in json.load(open(sets["val"]))['categories']}

for stage, filename in sets.items():
    print("Parse", filename)
    data = json.load(open(filename))

    images = {}
    for im in tqdm(data["images"], "Parse Images"):
        img = base_dict(im['coco_url'], im['width'], im['height'], 3)
        images[im["id"]] = img

    for an in tqdm(data["annotations"], "Parse Annotations"):
        ann = base_object(images[an['image_id']]['annotation']["size"], cate[an['category_id']], an['bbox'])
        images[an['image_id']]['annotation']['object'].append(ann)

    for k, im in tqdm(images.items(), "Write Annotations"):
        im['annotation']['object'] = im['annotation']['object'] or [None]
        unparse(im,
                open(os.path.join(dst_dirs["Annotations"], "{}.xml".format(str(k).zfill(12))), "w"),
                full_document=False, pretty=True)

    print("Write image sets")
    with open(os.path.join(dst_dirs["ImageSets"], "{}.txt".format(stage)), "w") as f:
        f.writelines(list(map(lambda x: str(x).zfill(12) + "\n", images.keys())))

    print("OK")

# import os
# import shutil
# import lxml.etree
#
# headstr = """\
# <annotation>
#     <folder>VOC2012</folder>
#     <filename>%06d.jpg</filename>
#     <source>
#         <database>My Database</database>
#         <annotation>PASCAL VOC2012</annotation>
#         <image>flickr</image>
#         <flickrid>NULL</flickrid>
#     </source>
#     <owner>
#         <flickrid>NULL</flickrid>
#         <name>company</name>
#     </owner>
#     <size>
#         <width>%d</width>
#         <height>%d</height>
#         <depth>%d</depth>
#     </size>
#     <segmented>0</segmented>
# """
#
# objstr = """\
#     <object>
#         <name>%s</name>
#         <pose>Unspecified</pose>
#         <truncated>0</truncated>
#         <difficult>0</difficult>
#         <bndbox>
#             <xmin>%f</xmin>
#             <ymin>%f</ymin>
#             <xmax>%f</xmax>
#             <ymax>%f</ymax>
#         </bndbox>
#     </object>
# """
#
# tailstr = '''\
# </annotation>
# '''
#
#
# def parse_xml(xml):
#     if not len(xml):
#         return {xml.tag: xml.text}
#
#     result = {}
#
#     for child in xml:
#         child_result = parse_xml(child)
#
#         if child.tag != 'object':
#             result[child.tag] = child_result[child.tag]
#         else:
#             if child.tag not in result:
#                 result[child.tag] = []
#
#             result[child.tag].append(child_result[child.tag])
#
#     return {xml.tag: result}
#
#
# paths = os.listdir('../datasets/COCO_2014/VOCdevkit/VOC2012/Annotations')
# idx = 1
#
# for path in paths:
#     annotation_xml = os.path.join(
#         '../datasets/COCO_2014/VOCdevkit/VOC2012', 'Annotations', path)
#     annotation_xml = lxml.etree.fromstring(open(annotation_xml).read())
#     annotation = parse_xml(annotation_xml)['annotation']
#
#     if annotation['object'][0] != None:
#         list = []
#
#         for object in annotation['object']:
#             if object['name'] == 'book':
#                 list.append(object)
#
#         if len(list) != 0:
#             shutil.copyfile('../datasets/COCO_2014/VOCdevkit/VOC2012/JPEGImages/' + annotation['filename'],
#                             '../datasets/COCO_2014/VOCdevkit/VOC2012/new_images/%06d.jpg' % (idx))
#
#
#             if annotation['filename'][5] == 't':
#                 f1 = open('../datasets/COCO_2014/VOCdevkit/VOC2012/new_main/train.txt', 'a')
#                 f1.write('%06d\n' % (idx))
#                 f1.close()
#             else:
#                 f2 = open('../datasets/COCO_2014/VOCdevkit/VOC2012/new_main/val.txt', 'a')
#                 f2.write('%06d\n' % (idx))
#                 f2.close()
#
#             f3 = open('../datasets/COCO_2014/VOCdevkit/VOC2012/new_annotations/%06d.xml' % (idx), "w")
#             head = headstr % (idx, int(annotation['size']['width']), int(annotation['size']['height']), int(annotation['size']['depth']))
#             f3.write(head)
#
#             for book in list:
#                 xmin = float(book['bndbox']['xmin'])
#                 ymin = float(book['bndbox']['ymin'])
#                 xmax = float(book['bndbox']['xmax'])
#                 ymax = float(book['bndbox']['ymax'])
#
#                 f3.write(objstr % ('book', xmin, ymin, xmax, ymax))
#
#             # tail
#             f3.write(tailstr)
#             f3.close()
#             idx += 1

