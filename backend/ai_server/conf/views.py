import cv2
import base64
import numpy as np
import tensorflow as tf
from contents.detect import detect
from contents.yolov3.models import YoloV3
from django.conf import settings
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt

# 파라미터 설정
origin_yolo_max_boxes = 100
face_yolo_max_boxes = 10
origin_num_classes = 80
face_num_classes = 2
origin_weights = './contents/checkpoints/origin.tf'
face_weights = './contents/checkpoints/face.tf'

# GPU 메모리 설정
physical_devices = tf.config.experimental.list_physical_devices('GPU')
tf.config.experimental.set_memory_growth(physical_devices[0], True)

# 모델 생성
settings.ORIGIN_YOLO = YoloV3(classes=origin_num_classes, yolo_max_boxes=100)
settings.ORIGIN_YOLO.load_weights(origin_weights)
settings.FACE_YOLO = YoloV3(classes=face_num_classes, yolo_max_boxes=10)
settings.FACE_YOLO.load_weights(face_weights)


@csrf_exempt
def predict(request):
    # byte 단위의 이미지 불러오기
    image_bytes = request.FILES.get('data').file.getvalue()

    # byte 단위의 이미지를 1차원 int형 ndarray로 변환
    image = np.frombuffer(image_bytes, dtype=np.uint8)

    # 1차원에서 3차원 ndarray로 변환
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)

    # 집중 여부 판단
    image = detect(image)

    # 시각화된 3차원 ndarray를 1차원 int형 ndarray로 변환
    _, image = cv2.imencode('.png', image)

    # 1차원 ndarray를 byte 단위의 이미지로 변환
    image_bytes = image.tobytes()

    # Base64 인코딩
    image_bytes = base64.b64encode(image_bytes)

    return HttpResponse(image_bytes, content_type='application/octet-stream')
