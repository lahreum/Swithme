import cv2
import tensorflow as tf
from contents.yolov3.dataset import transform_images
from contents.yolov3.utils import detect_result
from django.conf import settings

# 파라미터 설정
size = 416
origin_class_names_path = './contents/data/class_name/origin.names'
face_class_names_path = './contents/data/class_name/face.names'
# hand_class_names_path = './contents/data/class_name/hand.names'

# 클래스 종류 불러오기
origin_class_names = [c.strip() for c in open(origin_class_names_path).readlines()]
face_class_names = [c.strip() for c in open(face_class_names_path).readlines()]
# hand_class_names = [c.strip() for c in open(hand_class_names_path).readlines()]


def detect(image, detect_cnt):
    # 모델 불러오기
    origin_yolo = settings.ORIGIN_YOLO
    face_yolo = settings.FACE_YOLO
    # hand_yolo = settings.HAND_YOLO

    # 이미지 가공
    image_in = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
    image_in = tf.expand_dims(image_in, 0)
    image_in = transform_images(image_in, size)

    # 객체 감지
    origin_boxes, origin_scores, origin_classes, origin_nums = origin_yolo.predict(image_in)
    face_boxes, face_scores, face_classes, face_nums = face_yolo.predict(image_in)
    # hand_boxes, hand_scores, hand_classes, hand_nums = hand_yolo.predict(image_in)

    # 결과
    result = detect_result((origin_boxes, origin_scores, origin_classes, origin_nums), origin_class_names, (face_boxes, face_scores, face_classes, face_nums), face_class_names)
    # image = draw_outputs(image, (face_boxes, face_scores, face_classes, face_nums), face_class_names)
    # image = draw_outputs(image, (hand_boxes, hand_scores, hand_classes, hand_nums), hand_class_names)

    return result
