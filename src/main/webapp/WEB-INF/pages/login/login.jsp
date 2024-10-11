<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>
<jsp:include page="/templates/header.jsp"></jsp:include>

<script>
  document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("ajax-submit").addEventListener("click", postLogin);
  })
  

  function postLogin() {
    let ajaxForm = document.getElementById("ajaxForm");
    let ajaxFormData = new FormData(ajaxForm);
    const data = {};

    if(!ajaxForm.checkValidity()) {
      document.getElementById("ajax-errorMsg").innerHTML = "帳密欄位格式有誤";
      return;
    }
    
    for(const [key, val] of ajaxFormData.entries()) {
      if(val === "") {
        document.getElementById("ajax-errorMsg").innerHTML = "帳密欄位不得為空";
        return;
      }
      data[key] = val;
    }

    // 專案名稱
    let contentPath = window.location.pathname.split("/")[1] || "";
    // 完整專案路徑
    let baseUrl = window.location.origin + "/" + contentPath + "/";

    // 提交至ajaxlogin
    fetch(baseUrl + "ajaxlogin", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    }) 
    .then(response => {

    if (!response.ok) {
      return response.json().then(errorData => {
        // 獲取錯誤消息
        return Promise.reject(errorData.message); // 這裡假設返回的對象中有 message 字段
      });
    }
    // 如果響應是成功的，轉換為 JSON
    return response.json();
    })
    .then(msg => {
      window.location.href = baseUrl;
    })
    .catch(error => {
      console.error(error);
      alert(error);
      return;
    })
    // ajaxlogin
  }
	
  
  
	
</script>
</head>
<body>
  <div class="container">
    <div class="row justify-content-around" style="height: 100vh;">
      <div class="row justify-content-around" style="height: 100vh;">
        <div id="login-form" class=" d-flex justify-content-center align-items-cente" >
          <div class="d-flex align-items-center">
            <div class="tab-content" id="pills-tabContent">
              <div class="tab-pane fade show active" id="login-div" role="tabpanel" aria-labelledby="pills-home-tab">
                <label for="acc" class="form-label">Controlle 登入</label>
                  <form method="post" action="login">
                    <div class="mb-3">
                      <label for="acc" class="form-label">帳號</label>
                      <input type="text" class="form-control" name="acc" maxlength="20" pattern="^[A-Za-z\d]{6,20}$" placeholder="請輸入6~20個字元" value="" required/>
                      <div id="emailHelp" class="form-text"></div>
                    </div>
                    <div class="mb-3">
                      <label for="pwd" class="form-label">密碼</label>
                      <input type="password" class="form-control" name="pwd" maxlength="20" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$" placeholder="包含英文數字的6~20個字元" value="" required/>
                    </div>
                    <div class="text-danger">${ requestScope.errorMsg }</div>
                    <a href="login/signup" class="">建立帳號</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="btn btn-primary">登入</button>
                  </form>
              </div>
              <div class="tab-pane fade" id="ajax-login-div" role="tabpanel" aria-labelledby="pills-profile-tab">
                <label for="acc" class="form-label">Ajax 登入</label>
                <form id="ajaxForm">
                    <div class="mb-3">
                      <label for="ajax-acc" class="form-label">帳號</label>
                      <input type="text" class="form-control" name="acc" maxlength="20" pattern="^[A-Za-z\d]{6,20}$" placeholder="請輸入6~20個字元" value="" required/>
                      <div id="emailHelp" class="form-text"></div>
                    </div>
                    <div class="mb-3">
                      <label for="ajax-pwd" class="form-label">密碼</label>
                      <input type="password" class="form-control" name="pwd" maxlength="20" pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,20}$" placeholder="包含英文數字的6~20個字元" value="" required>
                    </div>
                  <div id="ajax-errorMsg" class="text-danger">${ requestScope.errorMsg }</div>
                  <a href="login/signup" class="">建立帳號</a>
                  &nbsp;&nbsp;&nbsp;&nbsp;
                  <div id="ajax-submit" class="btn btn-primary">登入</div>
                </form>
              </div>
            </div>
          </div>
      
        </div>
      </div>
      <div class="row justify-content-around" style="height: 100vh;">
        <div class="d-flex align-items-center">
          <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
            <li class="nav-item" role="presentation">
              <button class="btn btn-primary nav-link active" id="pills-home-tab" data-toggle="pill" data-target="#login-div" type="button" role="tab" aria-controls="pills-home" aria-selected="true">Controller 登入</button>
            </li>
            &nbsp;
            <li class="nav-item" role="presentation">
              <button class="btn btn-primary nav-link" id="pills-profile-tab" data-toggle="pill" data-target="#ajax-login-div" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Ajax 登入</button>
            </li>
          </ul>
        </div>
      </div>
    </div>

  </div>

	

	
	<jsp:include page="/templates/footer.jsp"></jsp:include>
</body>
</html>