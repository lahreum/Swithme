import cv2
import time
import tensorflow as tf
from contents.yolov3_tf2.dataset import transform_images
from contents.yolov3_tf2.utils import draw_outputs
from django.conf import settings

size = 416
class_names = './contents/data/coco.names'
class_names = [c.strip() for c in open(class_names).readlines()]


def detect(image):
    yolo = settings.YOLO

    image_in = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    image_in = tf.expand_dims(image_in, 0)
    image_in = transform_images(image_in, size)

    times = []
    t1 = time.time()
    boxes, scores, classes, nums = yolo.predict(image_in)
    t2 = time.time()
    times.append(t2 - t1)
    times = times[-20:]

    image = draw_outputs(image, (boxes, scores, classes, nums), class_names)

    return image
