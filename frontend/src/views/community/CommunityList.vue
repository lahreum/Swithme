<template>
  <div>
    <div><p class="letter mainTitle">주제별 커뮤니티</p></div>
    <CategoryNav @categoryName="getCategory"/>
    <!-- 카테고리명, 정렬박스 -->
    <v-container>
      <v-row>
        <v-col cols="10"><p class="letter subTitle">{{ category.categoryName }} <v-icon>mdi-arrow-down-drop-circle-outline</v-icon></p></v-col>
        <v-col cols="2"><SelectBox @filterInfo="sortByFilter" style="padding-top:10px;"/></v-col>
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
              좋아요
            </th>
            <th class="text-center">
              조회수
            </th>
            <th class="text-center">
              등록일
            </th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="board in calData"
            :key="board.boardId" style="height:60px;" @click="moveToBoardDetail(board)"
          >
            <td class="text-center">{{ board.boardId }}</td>
            <td class="text-center">{{ board.boardTitle }}</td>
            <td class="text-center">{{ board.boardWriter }}</td>
            <td class="text-center">{{ board.boardLiked }}</td>
            <td class="text-center">{{ board.boardView }}</td>
            <td class="text-center">{{ board.boardDate }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div style="text-align:right;padding-top:20px;" @click="$router.push('/community/community-create')"><AppBtnSmall :btnColor="'#424242'" :btnName="'글쓰기'" :btnNameColor="'white'"/></div>
    <div class="text-center" style="padding-top:10px;">
      <v-pagination
        v-model="curPageNum" color="#673fb4"
        :length="numOfPages"
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
const storage = window.sessionStorage;

export default {
    components: {
        CategoryNav,
        SelectBox,
        AppBtnSmall
    },
    data: function() {
      return {
        filter: '',
        category: {
          categoryNum: 1,
          categoryName: '초중고',
        },
        boardList: [],
        curPageNum: 1,
        dataPerPage: 5,
      };
    },
    created() {
      this.category.categoryNum = `${this.$store.state.categoryId}`;
      this.category.categoryName = `${this.$store.state.categoryName}`;
      this.getBoardList(); 
    },
    watch: {
      categoryNum: function() {
        this.$store.commit("MOVETOBOARDCATEGORY", this.category);
      }
    },
    computed: {
      startOffset() {
        return ((this.curPageNum - 1) * this.dataPerPage);
      },
      endOffset() {
        return (this.startOffset + this.dataPerPage);
      },
      numOfPages() {
        return Math.ceil(this.boardList.length / this.dataPerPage);
      },
      calData() {   // 보여지고 있는 page의 숫자와 page당 보여질 data의 갯수에 따라서 계산된 startOffset과 endOffset을 이용해 slice 하여 return
        return this.boardList.slice(this.startOffset, this.endOffset)
      }
    },
    methods: {
      getCategory: function(value){
        this.category.categoryName = value.name; 
        this.category.categoryNum = value.value;
        this.$store.commit("MOVETOBOARDCATEGORY", value);
        this.getBoardList();
      },
      getBoardList() { 
        this.$Axios
        .get(`community/board?categoryId=`+ this.category.categoryNum,{
          headers: {
            "jwt-auth-token": storage.getItem("jwt-auth-token"),
          }
        })
        .then((res) => {
          if(Object.keys(res.data.boardList).length > 0) {
            this.boardList = res.data.boardList.reverse();
          } else {
            this.boardList = [];
          }
        })
        .catch((error) => {
          console.log(error);
        })
      },
      moveToBoardDetail(board) {
        this.$store.commit("MOVETOBOARDDETAIL", board.boardId);
        this.$router.push("/community/community-detail");
      },
      sortByFilter(value){
        this.filter = value;
        console.log('filter is ', value);
        // 필터순으로 다시 this.boardList 정렬하기
        if(value === '오래된 순') {
          // sort by boardList[i].boardDate
          this.boardList.sort(function(a,b){
            if(a.boardDate > b.boardDate) {
              return 1;
            } 
            if(a.boardDate < b.boardDate) {
              return -1;
            } 
            return 0;
            // a === b 일때
          })
        } else if(value === '좋아요 순') {
          // sort by boardList[i].boardLiked
          this.boardList.sort(function(a,b){
            if(a.boardLiked < b.boardLiked) {
              return 1;
            } 
            if(a.boardLiked > b.boardLiked) {
              return -1;
            } 
            return 0;
          })
        } else if(value === '조회수 순') {
          // sort by boardList[i].boardView
          this.boardList.sort(function(a,b){
            if(a.boardView < b.boardView) {
              return 1;
            } 
            if(a.boardView > b.boardView) {
              return -1;
            } 
            return 0;
          })
        } else if(value === '최신 순'){
          // sort by boardList[i].boardDate
          this.boardList.sort(function(a,b){
            if(a.boardDate < b.boardDate) {
              return 1;
            } 
            if(a.boardDate > b.boardDate) {
              return -1;
            } 
            return 0;
            // a === b 일때
          })
        }
      },
    }
}
</script>

<style>
.v-pagination__item {
  box-shadow: 0 !important;
}

</style>