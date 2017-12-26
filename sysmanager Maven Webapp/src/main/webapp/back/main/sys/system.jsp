<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>å¤§æ°æ®åæå¹³å°</title>
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/common.css"/>
    <link rel="stylesheet" href="/sysmanager/back/platform2/css/index.css"/>
    <script type="text/javascript" src="/sysmanager/back/platform2/js/libs/jquery-1.11.3.min.js"></script>
</head>

<body>
    <div class="contentRight">
       <div class="rightDownSel">
           <ul class="tabNav">
               <li class="on" onclick="query('station/showAll.jsp')">æ²¹ç«ç®¡ç</li>
               <li onclick="query('pos/showAll.jsp')">POSä¼ è¾IPç®¡ç</li>
              <!--  <li>èåæ¥ç®¡ç</li>
               <li>æ ç­¾ç®¡ç</li> -->
           </ul>
            
           <!-- <div class="rightDownMain">
               <div class="downDetails" style="display: block;">è¿æ¯ç¬¬ä¸ä¸ªåå®¹åºå
                   <iframe src="station/showAll.jsp" class="introduceNav" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" ></iframe>
               </div>
               <div class="downDetails">è¿éå°±æ¯ä¸é¢éæ©å®æçåå®¹ 2
                	<iframe src="pos/showAll.jsp" class="introduceNav" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" ></iframe>
               </div>
               <div class="downDetails">
					<iframe src="festival/showAll.jsp" class="introduceNav" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" ></iframe>
				</div> --><!-- ç¬¬ä¸ä¸ªéé¡¹çåå®¹
               <div class="downDetails">
               		<iframe src="tag/showAll.jsp" class="introduceNav" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" ></iframe>
               </div> ç¬¬åä¸ªéé¡¹çåå®¹
           </div>-->
           <div id="content"></div>
           <script type="text/javascript">
			function query(url){
				$("#content").empty();
				$.ajax({
						url: url,//ç®æ é¡µé¢
						dataType: "html",
						type: "GET",
						cache: false,
						success: function(html){
							$("#content").html(html);
						}
					});
			}
			$(function(){
				query("station/showAll.jsp");
		});
		</script>
       </div> 
      
    </div>

    <!--å³è¾¹ ç»æ-->
</body>
<script type="text/javascript" src="/sysmanager/back/platform2/js/libs/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/sysmanager/back/platform2/js/index.js"></script>
<script type="text/javascript">navLeft();downTab();rightDown();</script>
</html>
