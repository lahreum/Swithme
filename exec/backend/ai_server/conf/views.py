import cv2
import numpy as np
from contents.detect import detect
from contents.yolov3.models import YoloV3
from django.conf import settings
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt

# 파라미터 설정
origin_yolo_max_boxes = 10
face_yolo_max_boxes = 10
origin_num_classes = 80
face_num_classes = 2
origin_weights = './contents/checkpoints/origin.tf'
face_weights = './contents/checkpoints/face.tf'

# 모델 생성
settings.ORIGIN_YOLO = YoloV3(classes=origin_num_classes, yolo_max_boxes=origin_yolo_max_boxes)
settings.ORIGIN_YOLO.load_weights(origin_weights)
settings.FACE_YOLO = YoloV3(classes=face_num_classes, yolo_max_boxes=face_yolo_max_boxes)
settings.FACE_YOLO.load_weights(face_weights)


@csrf_exempt
def predict(request):
    # byte 단위의 이미지 불러오기
    image_bytes = request.FILES.get('image').file.getvalue()

    # byte 단위의 이미지를 1차원 int형 ndarray로 변환
    image = np.frombuffer(image_bytes, dtype=np.uint8)

    # 1차원에서 3차원 ndarray로 변환
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)

    # 객체 감지
    result = detect(image)

    return JsonResponse(result, safe=False)
