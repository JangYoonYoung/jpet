<%@ include file="IncludeTop.jsp"%>

<table>
  <tr>
    <td style="text-align: left; vertical-align: top; width: 20%">
      <table id="main-menu">
        <tr>
          <td><a href='<c:url value="/shop/viewCart.do"/>'><b>
            <font color="black" size="2">&lt;&lt; Shopping Cart</font></b></a></td>
        </tr>
      </table>
    </td>

    <td style="text-align: center; vertical-align: top">
      <h2>Checkout Summary</h2>
      <table class="n25">
        <tr bgcolor="#CCCCCC">
          <td><b>Item ID</b></td>
          <td><b>Product ID</b></td>
          <td><b>Description</b></td>
          <td><b>In Stock?</b></td>
          <td><b>Quantity</b></td>
          <td><b>List Price</b></td>
          <td><b>Total Cost</b></td>
        </tr>
        <c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
          <tr bgcolor="#FFFF88">
            <td><b> 
              <a href='<c:url value="/shop/viewItem.do">
                <c:param name="itemId" value="${cartItem.item.itemId}"/></c:url>'>
                  <c:out value="${cartItem.item.itemId}" />
              </a></b>
            </td>
            <td><c:out value="${cartItem.item.productId}" /></td>
            <td>
              <c:out value="${cartItem.item.attribute1}" /> 
              <c:out value="${cartItem.item.attribute2}" /> 
              <c:out value="${cartItem.item.attribute3}" /> 
              <c:out value="${cartItem.item.attribute4}" /> 
              <c:out value="${cartItem.item.attribute5}" /> 
              <c:out value="${cartItem.item.product.name}" />
            </td>
            <td align="center"><c:out value="${cartItem.inStock}" /></td>
            <td align="center"><c:out value="${cartItem.quantity}" /></td>
            <td align="right"><fmt:formatNumber
                value="${cartItem.item.listPrice}" pattern="$#,##0.00" /></td>
            <td align="right"><fmt:formatNumber
                value="${cartItem.totalPrice}" pattern="$#,##0.00" /></td>
          </tr>
        </c:forEach>
        <tr bgcolor="#FFFF88">
          <td colspan="7" align="right"><b>Sub Total: <fmt:formatNumber
                value="${cart.subTotal}" pattern="$#,##0.00" /></b><br /></td>
        </tr>
      </table>

      <c:if test="${!cart.cartItemList.firstPage}">
        <a href="checkout.do?page=previousCart"><font color="green">
          <B>&lt;&lt; Prev</B></font></a>
      </c:if>
      <c:if test="${!cart.cartItemList.lastPage}">
        <a href="checkout.do?page=nextCart"><font color="green">
          <B>Next &gt;&gt;</B></font></a>
      </c:if>
      <br> 
      <a href='<c:url value="/shop/newOrder.do"/>'>
        <img border="0" src="../images/button_continue.gif" alt="" /></a>
        
      <!-- 카카오 페이 -->

		<form method="post" action="https://mockup-pg-web.kakao.com/v1/befec873fad0f17514f30f49e32c769fa8f21fdf717187acf6b9817ccf9ed75e/info">
			<input type="hidden" name="partner_order_id" value="${ordernum}"> <!-- 주문번호 -->
			<input type="hidden" name="partner_user_id" value="${session.userid}"> <!-- 주문자 id -->
			<input type="hidden" name="item_name" value="${cartItem.item.product.name}"> <!-- 상품명 -->
			<input type="hidden" name="quantity" value="${cartItem.quantity}"> <!-- 수량  -->
			<input type="hidden" name="total_amount" value="${cart.subTotal}"> <!--총 가격 -->
			<input type="hidden" name="tax_free_amount" value="0"> <!-- 비과세 -->

			<input type="image" src="../images/kakaopay.png" height="30" width="100">
		</form>
    </td>
    <td style="text-align: right; vertical-align: top; width: 20%">&nbsp;</td>
  </tr>
</table>

<%@ include file="IncludeBottom.jsp"%>
