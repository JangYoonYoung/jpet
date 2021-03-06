<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!empty message}">
  <b><font color="red"><c:url value="${message}" /></font></b>
</c:if>

<div align="center">
  <form action='<c:url value="/shop/signon.do"/>' method="POST">
    <c:if test="${!empty signonForwardAction}">
      <input type="hidden" name="forwardAction"
        value='<c:url value="${signonForwardAction}"/>' />
    </c:if>
    <table>
      <tr>
        <td colspan="2">Please enter your username and password. <br />&nbsp;
        </td>
      </tr>
      <tr>
        <td>Username:</td>
        <td><input type="text" name="username" size="20" value="j2ee" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="password" name="password" size="20" value="j2ee" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input name="update" type="image" src="../images/button_submit.gif" /></td>
      </tr>
    </table>
  </form>
  <a href='<c:url value="/shop/newAccount.do"/>'> 
    <img border="0" src="../images/button_register_now.gif" alt="" />
  </a>

  <!-- 카카오로그인  -->
  <a href='https://kauth.kakao.com/oauth/authorize?client_id={287704ee46a1efda52da413f01967801}&redirect_uri=http://{/kakao/kakaologin.do}}&response_type=code'>
  	<img border="0" src="../images/kakaologin.png" alt="" width="120" height="30"/>
  </a>

</div>