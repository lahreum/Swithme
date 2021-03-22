import config_sk
from data import preprocess_sk
from utils import utils_sk


# config 저장
utils_sk.save_config(config_sk)


# 이미지 경로 및 캡션 불러오기
image_name_list, comment_list = preprocess_sk.get_path_caption(config_sk.args.caption_file_path)


# 전체 데이터셋을 분리해 저장하기
train_dataset_path, test_dataset_path = preprocess_sk.dataset_split_save(image_name_list, comment_list)


# 저장된 데이터셋 불러오기
image_name_list, comment_list = preprocess_sk.get_data_file(train_dataset_path, test_dataset_path, is_train_data=True)


# 데이터 샘플링
if config_sk.do_sampling:
    image_name_list, comment_list = preprocess_sk.sampling_data(config_sk.args.sampling_ratio, image_name_list, comment_list)


# 이미지와 캡션 시각화 하기
utils_sk.visualize_img_caption(config_sk.args.image_file_path, image_name_list, comment_list)
