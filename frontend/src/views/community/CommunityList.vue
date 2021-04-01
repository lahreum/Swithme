<template>
  <div>
    <div><p class="letter mainTitle">주제별 커뮤니티</p></div>
    <CategoryNav/>
    <!-- 카테고리명, 정렬박스 -->
    <v-container>
      <v-row>
        <v-col cols="10"><p class="letter subTitle">취업 <v-icon>mdi-arrow-down-drop-circle-outline</v-icon></p></v-col>
        <v-col cols="2" ><SelectBox/></v-col>
      </v-row>
    </v-container>
    <hr class="lineStyle">
    <!-- 게시글 리스트 -->
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-center" >
              번호
            </th>
            <th class="text-center">
              글 제목
            </th>
            <th class="text-center">
              작성자
            </th>
            <th class="text-center">
              등록일
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="board in boards"
            :key="board.id" style="height:60px;"
          >
            <td class="text-center">{{ board.id }}</td>
            <td class="text-center">{{ board.title }}</td>
            <td class="text-center">{{ board.writer }}</td>
            <td class="text-center">{{ board.date }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div style="text-align:right;padding-top:20px;" @click="$router.push('/community/community-create')"><AppBtnSmall :btnColor="'#424242'" :btnName="'글쓰기'" :btnNameColor="'white'"/></div>
    <div class="text-center" style="padding-top:10px;">
      <v-pagination
        v-model="page" color="#673fb4"
        :length="5"
        circle
      ></v-pagination>
    </div>
  </div>
</template>

<script>
import CategoryNav from '@/components/common/CategoryNav.vue';
import SelectBox from '@/components/common/SelectBox.vue';
import AppBtnSmall from '@/components/common/AppBtnSmall.vue';
import "./community.css";
import "../user/user.css";

export default {
    components: {
        CategoryNav,
        SelectBox,
        AppBtnSmall
    },
    data: function() {
      return {
        page: 1,
        boards: [
          {
            id: 1,
            title: '2021 정보처리기사 실기 1회 합격 전략~~ 여기 모여라',
            writer: 'dlwlrma',
            date: '2021-03-13 23:02'
          },
          {
            id: 2,
            title: '시험장 갈때 신분증 꼭 챙겨가세요.... ',
            writer: '빛봉현',
            date: '2021-03-13 21:15'
          },
          {
            id: 3,
            title: '3일만에 정처기 따는법',
            writer: '별빛지현',
            date: '2021-03-13 20:31'
          },
          {
            id: 4,
            title: '정처기 4,5회 실기 마지막 점검자료 공유합니다!!!!',
            writer: '녹용파는사슴',
            date: '2021-03-13 20:17'
          },
          {
            id: 5,
            title: '아니 이번 실기 문제 뭐야? 이상해...',
            writer: '취준생',
            date: '2021-03-13 20:11'
          },
          {
            id: 6,
            title: '드디어 붙음 ㅠㅠㅠㅠㅠ',
            writer: '피카츄',
            date: '2021-03-12 23:19'
          },
          {
            id: 7,
            title: '커뮤니티 물 흐리지 말장',
            writer: 'gkdldya22',
            date: '2021-03-12 22:42'
          },
          {
            id: 8,
            title: '수제비 1권 302페이지 2번 해설 이해가 안돼요',
            writer: '인국공21',
            date: '2021-03-12 20:03'
          },
          {
            id: 9,
            title: '하루에 20시간 공부하시는 분도 잇네요...와..',
            writer: '상반기취뽀',
            date: '2021-03-12 19:34'
          },
          {
            id: 10,
            title: '문제 너무 지엽적이다...',
            writer: '해피해피',
            date: '2021-03-12 18:27'
          },
        ],
      };
  },
}
</script>

<style>
.v-pagination__item {
  box-shadow: 0 !important;
}

</style>