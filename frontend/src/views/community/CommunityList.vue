<template>
  <div>
    <div><p class="letter mainTitle">주제별 커뮤니티</p></div>
    <CategoryNav @categoryName="getCategory"/>
    <!-- 카테고리명, 정렬박스 -->
    <v-container>
      <v-row>
        <v-col cols="10"><p class="letter subTitle">{{ categoryName }} <v-icon>mdi-arrow-down-drop-circle-outline</v-icon></p></v-col>
        <v-col cols="2"><SelectBox style="padding-top:10px;"/></v-col>
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
            v-for="board in boardList"
            :key="board.boardId" style="height:60px;" @click="$router.push('/community/community-detail')"
          >
            <td class="text-center">{{ board.boardId }}</td>
            <td class="text-center">{{ board.boardTitle }}</td>
            <td class="text-center">{{ board.boardWriter }}</td>
            <td class="text-center">{{ board.boardDate }}</td>
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
        categoryNum: 1,
        categoryName: '',
        boardList: [
          
        ],
      };
    },
    created() {
      this.getBoardList(); 
    },
    methods: {
      getCategory: function(name, value){
        this.categoryName = name; 
        this.categoryNum = value;
      },
      getBoardList() {  // 헤더담기 -> 토큰 올려서 커뮤니티 보드에 요청하기.
        // this.$Axios
        // .get(`community/board?categoryId=`+ this.categoryNum)
        // .then((res) => {
        //   if(Object.keys(res.data.boardList).length > 0) {
        //     console.log('게시글 목록은 일단 들어옴');
        //     this.boardList = res.data.boardList;
        //   } else {
        //     alert('게시글 목록 로딩 실패');
        //   }
        // })
        // .catch((error) => {
        //   console.log(error);
        // })
      }
    },
}
</script>

<style>
.v-pagination__item {
  box-shadow: 0 !important;
}

</style>