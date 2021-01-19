//2012-09-12 win_open(),win_close()  对话框函数
//JS 打开新窗口函数
//自适应窗口：
//2013-02-20 增加了最小高度和宽度，如果设定值小于min值，自适应
//     例如：nWidth = 0, nHeight = 0 - 高度和宽度同时自适应  nWidth = 800, nHeight = 0 - 高度自适应   nWidth = 0, nHeight = 500 - 宽度自适应
//jsOpts={top:xx,    Y轴坐标，默认：'38.2%'
//        left: xx,  X轴坐标，默认：'50%'
//        max: true, 是否显示最大化按钮
//        min: true, 是否显示最小化按钮
//        lock：true} 是否锁屏

function win_openSet(strUrl, strTitle, nWidth, nHeight, jsOpts, callback, nzIndex, btnCancel) {

    if (nWidth < 0) nWidth = 0;
    if (nHeight < 0) nHeight = 0;
    if (nzIndex == null || nzIndex == undefined) nzIndex = 1000;

    if (strUrl.indexOf("~/") >= 0) {
        strUrl = strUrl.replace("~", getRootPath());
    }

    //var nIndex = parseInt(getCookie("dlg_nIndex")) || 100;  //初始可能为空，parseInt=NaN 强制赋100
    var nId = "dlg_" + hashCode(strUrl);
    var regUrl = /([\w.]+)[?]([\w.]+)/gi;
    var strRet = strUrl.match(regUrl);
    if (strRet != null) {
        strUrl = strUrl + "&nIdlg=" + nId;
    }
    else {
        strUrl = strUrl + "?nIdlg=" + nId;
    }
    var opts = {
        id: nId,
        title: strTitle,
        content: "url:" + strUrl,
        width: nWidth,
        height: nHeight,
        cancelVal: '\u5173\u95ED',
        lock: true,       //背景是否锁定
        cancel: btnCancel,//取消关闭按钮
        zIndex: nzIndex,  //显示弹出框的排序
        drag: false,      //是否允许拖拽
        close:false,
        max: false,
        min: false
    };

    //if (jsOpts != null) {
    //    if (myUtil.json_key(jsOpts, "top") == true) opts.top = jsOpts.top;
    //    if (myUtil.json_key(jsOpts, "left") == true) opts.left = jsOpts.left;
    //    if (myUtil.json_key(jsOpts, "max") == true) opts.max = jsOpts.max;
    //    if (myUtil.json_key(jsOpts, "min") == true) opts.min = jsOpts.min;
    //    if (myUtil.json_key(jsOpts, "lock") == true) opts.lock = jsOpts.lock;
    //}
    if (callback != null) {
        opts.select = callback;
    }
    var dgRet = new $.dialog(opts);
    dgRet.show();
   
    return dgRet;
}


function hashCode(str) {
    var hash = 0;
    if (str.length == 0) return hash;
    var nLen = str.length;
    for (var i = 0; i < nLen; i++) {
        var char = str.charCodeAt(i);
        hash = ((hash << 5) - hash) + char;
        hash = hash & hash; // Convert to 32bit integer
    }
    return hash;
}


//多个弹出框时,可以设定弹出框前后顺序  nzIndex参数默认1000,越大越靠前
function win_openIndex(strUrl, strTitle, nWidth, nHeight, nzIndex) {
    win_openSet(strUrl, strTitle, nWidth, nHeight, false, null, nzIndex)
}

//是否显示关闭按钮
function win_openNoBtn(strUrl, strTitle, nWidth, nHeight, btnCancel) {
    win_openSet(strUrl, strTitle, nWidth, nHeight, false, null, null, btnCancel)
}

//可自定义设定弹出框前后位置和是否显示关闭按钮
function win_openNew(strUrl, strTitle, nWidth, nHeight, nzIndex, btnCancel) {
    win_openSet(strUrl, strTitle, nWidth, nHeight, false, null, nzIndex, btnCancel)
}