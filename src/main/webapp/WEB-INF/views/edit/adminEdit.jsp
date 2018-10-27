<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>SKHU졸업관리시스템</title>
		
	<!-- Bootstrap -->
	<link href="${R}res/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${R}res/css/font-awesome.min.css">
	<link rel="stylesheet" href="${R}res/css/animate.css">
	<link rel="stylesheet" href="https://icono-49d6.kxcdn.com/icono.min.css">
	<link href="${R}res/css/prettyPhoto.css" rel="stylesheet">
	<link href="${R}res/css/style.css" rel="stylesheet" />
		
		
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
	<!--style-->
	<style>
select option:checked,
select option:hover {     
      background: rgb(226, 240, 217);
      color: rgb(88, 88, 88); 
    }
    
    .contents{
      width: 1000px;
      margin-left: 100px;
      margin-right: 100px;
    }

    .btn-submit {
      padding: 8px 20px;
      background: #1BBD36;
      color: #fff;
      border-radius: 4px;
      border:none;
      margin-top: 10px;
    }
    .group{
      margin-top: -10px;
      margin-left: 60px;
      margin-right: 60px;
      height: 180px;

    }
    .edit{
      margin-top: -50px;
      margin-left: 80px;
      margin-right: 80px;
    }

            /* Style The Dropdown Button */
  .dropbtn {
      background-color: #4CAF50;
      color: white;
      padding: 16px;
      font-size: 16px;
      border: none;
      cursor: pointer;
  }

  /* The container <div> - needed to position the dropdown content */
  .dropdown {
      position: relative;
      display: inline-block;
  }

  /* Dropdown Content (Hidden by Default) */
  .dropdown-content {
      display: none;
      position: absolute;
      background-color :  #4CAF50;
      min-width: 160px;
      box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
      z-index: 1;
  }

  /* Links inside the dropdown */
  .dropdown-content a {
      color: white;
      padding: 12px 16px;
      text-decoration: none;
      display: block;
  }

  /* Change color of dropdown links on hover */
  .dropdown-content a:hover {background-color: rgb(96, 185, 96)}

  /* Show the dropdown menu on hover */
  .dropdown:hover .dropdown-content {
      display: block;
  }

  /* Change the background color of the dropdown button when the dropdown content is shown */
  .dropdown:hover .dropbtn {
      background-color: #3e8e41;
  }
  </style>
</head>
<body>
<header>
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="navigation">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse.collapse">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
            <div class="navbar-brand">
              <a href="indexForAdmin.html"><h1><span>SKHU</span>졸업관리시스템</h1></a>
            </div>
          </div>

          <div class="navbar-collapse collapse">
            <div class="menu">
              <ul class="nav nav-tabs" role="tablist">
                <li role="presentation"><a href="">졸업요건</a></li>
                <li role="presentation" class="dropdown" ><a href="">졸업관리</a>
                  <div class="dropdown-content">
                      <a href="">졸업요건 관리</a>
                      <a href="">대체 과목 리스트 관리</a>
                      <a href="majorAdmitManage.html">전공인정관리</a>
                    </div>
                </li>
                <li role="presentation"><a href="http://www.skhu.ac.kr">학교홈페이지</a></li>
                <li role="presentation"><a href="https://forest.skhu.ac.kr/">종합정보시스템</a></li>
                <li role="presentation" class="dropdown" ><a href="StudentInquiryForAdmin.html">사용자관리</a>
                  <div class="dropdown-content">
                    <a href="graduation/professorInquiry">교수조회</a>
                    <a href="graduation/studentListForAdmin">학생조회</a>

                  </div>
                </li>
                <li role="presentation"><a href="user/logout_processing">Logout</a></li>
                <li>
                  
                  <div style="margin-top:28px; margin-left:1.5px;">
                      <a href="managerInfoEdit.html">
                          <i class="fas fa-user-edit fa-lg"></i>
                      </a>
                  </div>
              </li>

              </ul>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </header>
  <div id="breadcrumb">
        <div class="container">
          <div class="breadcrumb">
            <li>
            </li>
            <li style="font-size: 20px; margin-left: -5px;">
                개인정보수정
            </li>
          </div>
          <hr>
        </div>
      </div>

        <div class="contents">
            <div class="group">
                <h4><img src="images/abouti/manager.png" width="80px">&nbsp&nbsp&nbsp<font color="#DF4D4D">manager1</font> 님은 [<b> 관리자 </b>] 회원입니다.</h4>
                <hr>
            </div>
            <div class="edit">
                <p>아이디 <input type="text" class="form-control" name="id" value="manager1" readonly></p>
                <p>비밀번호 <input type="password" class="form-control" name="password" value="123123"></p>
                <p>비밀번호 확인 <input type="password" class="form-control" name="password" value="123123"></p> 
                <p>이메일 <input type="email" class="form-control" name="email" value="test123@skhu.net"></p>
                       
                
                <br>
                
                <p>
                    <input type="submit" class="btn-submit" value="저장" style="float: right;">    
                </p>
            </div>
        </div>
  

  </div>

  <br>
  <br>
  <br>

  <footer>
      <div class="footer">
          <div class="container">
            문의:test123@naver.com<br>
            152-716 서울시 구로구 연동로320<br>
            Copyright&copy; 2018SKHU Software Engineering All Rights Reserved<br>


          </div>
        </div>
  </footer>
  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
  <script src="${R}res/js/jquery-2.1.1.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="${R}res/js/bootstrap.min.js"></script>
  <script src="${R}res/js/jquery.prettyPhoto.js"></script>
  <script src="${R}res/js/jquery.isotope.min.js"></script>
  <script src="${R}res/js/wow.min.js"></script>
  <script src="${R}res/js/functions.js"></script>
</body>
</html>