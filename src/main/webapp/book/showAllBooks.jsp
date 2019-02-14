<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script src="../js/popper.min.js"></script>
<script src="../js/plugins.js"></script>
<script src="../js/classy-nav.min.js"></script>
<script src="../js/active.js"></script>
<script src="../js/vue.min.js"></script>
<script src="../js/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="../css/core-style.css">
<link rel="stylesheet" href="../style.css">
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/list-meizu.css" />
<link rel="stylesheet" href="../css/layout-meizu.css" />
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	Vue.component('book-item', {
		props : [ 'bookId', 'bookName', 'imgUrl', 'caption', 'price' ],
		template : `
		<li class="gl-item" data-init="0">
            <a data-mtype="store_list_kw_1" data-bh="click_store_list_kw_1" target="_self"
                :href="'/XinhuaBookstore/servlet/BookServlet?type=getBookById&id='+bookId" style="text-decoration:none"
                title="">
                 <div class="gl-item-wrap">
                     <div class="mod-pic">
                         <img class="lazy j-modProduct" :src="imgUrl" style="height:220px;">
                     </div>

                     <div class="slide-btn j-modBtns">
                 <span data-mtype="store_list_kw_1_clr_l" data-bh="click_store_list_kw_1_clr_l"
                       class="prev iconfont disabled">&#xe65b;</span>
                         <span data-mtype="store_list_kw_1_clr_r" data-bh="click_store_list_kw_1_clr_r"
                               class="next iconfont">&#xe659;</span>
                     </div>

                     <h2>{{ bookName }}</h2>
                     <h3 class="gray" title="{{ caption }}">
                     {{caption}}
                     </h3>
                     <dd class="mod-price">
                         <span>￥</span>
                         <span class="vm-price">{{ price }}</span>
                     </dd>
                 </div>
             </a>
         </li>`
	})
</script>
<title>所有图书</title>
</head>

<body>
	<header class="header_area">
	<div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
		<div class="header-meta d-flex clearfix justify-content-end">
			<!-- Search Area -->
			<div class="search-area">
				<form action="/XinhuaBookstore/servlet/BookServlet?type=getBooksByName" method="post">
					<input type="search" name="name" id="headerSearch" placeholder="搜索您需要的商品">
					<button type="submit">
						<i class="fa fa-search" aria-hidden="true"></i>
					</button>
				</form>
			</div>
		</div>
	</div>
	</header>
	<div class="container" id="container" style="margin-top: 100px;margin-left: 17%;">
		<section class="goods-list" id="goodsList">
		<ul class="goods-list-wrap clearfix" id="goodsListWrap">
			<book-item v-for="book in books" v-bind:key="book.bookId" v-bind:book-id="book.bookId" v-bind:book-name="book.bookName" v-bind:img-url="book.imgUrl" v-bind:caption="book.caption" v-bind:price="book.price"> </book-item>
		</ul>
		</section>
		<ul class="pagination" id="pagination"></ul>
	</div>
</body>
<script type="text/javascript">
	var vm=new Vue({
		  el: '#container',
		  data: {
		    allBooks:${requestScope.books},
		    currentPage:1,
		    pageSize:8,
		  },
		  computed: {
			  books:function(){
				  return this.allBooks.slice((this.currentPage-1)*this.pageSize,this.currentPage*this.pageSize);
			  },
			  totalPages:function(){
				  return Math.ceil(this.allBooks.length/this.pageSize);
			  }
		  },
		  methods:{
			  changePage:function(page){
				  this.currentPage=page;
			  },
			  prePage:function(){
				  if(this.currentPage>1)
					  this.currentPage--;
			  },
			  nextPage:function(){
				  if(this.currentPage<this.totalPages)
					  this.currentPage++;
			  }
		  }
	});
	$(function(){
		//添加分页按钮
		var pages=`<li><span onclick="vm.prePage()">&laquo;</span></li>`;
		for(var i=1;i<=vm.totalPages;i++){
			pages+='<li><span onclick="vm.changePage('+i+')">'+i+'</span></li>'
		}
		pages+=`<li><span onclick="vm.nextPage()">&raquo;</span></li>`
		console.log(pages);
		$('#pagination').html(pages);
	})
</script>
</html>
