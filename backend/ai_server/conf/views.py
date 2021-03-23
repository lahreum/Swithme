import cv2
import base64
import numpy as np
from contents import detect
from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt


@csrf_exempt
def predict(request):
    # byte 단위의 이미지 불러오기
    image_bytes = request.FILES.get('data').file.getvalue()

    # byte 단위의 이미지를 1차원 int형 ndarray로 변환
    image = np.frombuffer(image_bytes, dtype=np.uint8)

    # 1차원에서 3차원 ndarray로 변환
    image = cv2.imdecode(image, cv2.IMREAD_COLOR)

    # ------------ predict ------------

    # ---------------------------------

    # 시각화된 3차원 ndarray를 byte 단위의 이미지로 변환

    # ---------------------------------

    # Base64 인코딩
    image_bytes = base64.b64encode(image_bytes)

    return HttpResponse(image_bytes, content_type='application/octet-stream')
