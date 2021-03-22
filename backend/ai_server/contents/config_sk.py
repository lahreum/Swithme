import argparse


parser = argparse.ArgumentParser()
parser.add_argument('--caption_file_path', type=str, default='.\\datasets\\captions.csv')
parser.add_argument('--image_file_path', type=str, default='.\\datasets\\images')
parser.add_argument('--checkpoint_path', type=str, default='.\\checkpoints')

parser.add_argument('--do_sampling', action='store_true')
parser.add_argument('--sampling_ratio', type=float, default=0.5)
parser.add_argument('--batch_size', type=int, default=256)

args = parser.parse_args()

do_sampling = args.do_sampling
