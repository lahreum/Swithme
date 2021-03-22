import csv
import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split


# 이미지 경로 및 캡션 불러오기
def get_path_caption(caption_file_path):
    caption_file = open(caption_file_path, 'r')
    data = csv.reader(caption_file)

    image_name_list = []
    comment_list = []

    for line in data:
        sep = line[0].split('|')

        if sep[0] == 'image_name':
            continue

        image_name_list.append(sep[0])
        comment_list.append(sep[2])

    caption_file.close()

    return image_name_list, comment_list


# 전체 데이터셋을 분리해 저장하기
def dataset_split_save(image_name_list, comment_list):
    # 데이터 가공
    processed_data = []
    bind_list = []
    prev_image_name = ''

    for idx, curr_image_name in enumerate(image_name_list):
        if prev_image_name != curr_image_name:
            if prev_image_name != '':
                processed_data.append(bind_list)
                bind_list = []

            bind_list.append(curr_image_name)
            bind_list.append(comment_list[idx])
            prev_image_name = curr_image_name
            continue

        bind_list.append(comment_list[idx])
        prev_image_name = curr_image_name

        if idx == len(image_name_list)-1:
            processed_data.append(bind_list)

    # 데이터 분리
    train_data, test_data = train_test_split(processed_data, test_size=0.2)

    # 데이터 저장
    train_dataset_path = './datasets/train'
    test_dataset_path = './datasets/test'

    np.save(train_dataset_path, train_data)
    np.save(test_dataset_path, test_data)

    return train_dataset_path, test_dataset_path


# 저장된 데이터셋 불러오기
def get_data_file(train_dataset_path, val_dataset_path, is_train_data=True):
    data = None

    if is_train_data:
        data = np.load(train_dataset_path + '.npy')
    else:
        data = np.load(val_dataset_path + '.npy')

    image_name_list = data[:, 0]
    comment_list = data[:, 1:]

    return image_name_list, comment_list


# 데이터 샘플링
def sampling_data(sampling_ratio, image_name_list, comment_list):
    image_name_df = pd.DataFrame(image_name_list)
    image_name_sr = image_name_df[0].sample(frac=sampling_ratio, random_state=1)

    sampled_idx_list = image_name_sr.index
    sampled_image_name_list = image_name_sr.values
    sampled_comment_list = []

    for idx in sampled_idx_list:
        sampled_comment_list.append(comment_list[idx])

    sampled_comment_list = np.array(sampled_comment_list)
    print('샘플링 개수: (%d/%d) - %d%%' % (len(sampled_image_name_list), len(image_name_list), sampling_ratio*100))

    return sampled_image_name_list, sampled_comment_list
