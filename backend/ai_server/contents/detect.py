import cv2
import tensorflow as tf
from contents.yolov3_tf2.dataset import transform_images
from contents.yolov3_tf2.utils import draw_outputs
from django.conf import settings

# 파라미터 설정
size = 416
class_names = './contents/data/coco.names'
# class_names = './contents/data/studywithme.names'
class_names = [c.strip() for c in open(class_names).readlines()]


def detect(image):
    # 모델 불러오기
    yolo = settings.YOLO

    # 이미지 가공
    image_in = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    image_in = tf.expand_dims(image_in, 0)
    image_in = transform_images(image_in, size)

    # 집중 여부 판단
    boxes, scores, classes, nums = yolo.predict(image_in)

    # 이미지 시각화
    image = draw_outputs(image, (boxes, scores, classes, nums), class_names)

    return image
