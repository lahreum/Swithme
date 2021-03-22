import matplotlib.image as img
import matplotlib.pyplot as plt
import numpy as np


# 세팅 값 저장
def save_config(config):
	settings = {}
	args = config.args

	settings['caption_file_path'] = args.caption_file_path
	settings['image_file_path'] = args.image_file_path
	settings['checkpoint_path'] = args.checkpoint_path
	settings['do_sampling'] = args.do_sampling
	settings['sampling_ratio'] = args.sampling_ratio
	settings['batch_size'] = args.batch_size

	np.save('./datasets/config', settings)


# 이미지와 캡션 시각화
def visualize_img_caption(image_file_path, image_name_list, comment_list):
	for image_idx, image_name in enumerate(image_name_list):
		comments = ''
		text_height = -100
		plt.subplots_adjust(top=0.75, right=0.75)

		for comment_idx, comment in enumerate(comment_list[image_idx]):
			plt.text(0, text_height, comment)
			text_height += 20

		image = img.imread(image_file_path+'\\'+image_name)
		plt.imshow(image)
		plt.show()
