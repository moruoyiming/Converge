var sdk = {};
sdk.os = {};
sdk.os.isIOS = /iOS|iPhone|iPad|iPod/i.test(navigator.userAgent);
sdk.os.isAndroid = !sdk.os.isIOS;
sdk.callbackname = function(){
    return "sdk_api_callback_" + (new Date()).getTime() + "_" + Math.floor(Math.random() * 10000);
};
sdk.callbacks = {};
sdk.addCallback = function(name,func,userdata){
    delete sdk.callbacks[name];
    sdk.callbacks[name] = {callback:func,userdata:userdata};
};

sdk.callback = function(para){
    var callbackobject = sdk.callbacks[para.callbackname];
    if (callbackobject !== undefined){
        if (callbackobject.userdata !== undefined){
            callbackobject.userdata.callbackData = para;
        }
        if(callbackobject.callback != undefined){
            var ret = callbackobject.callback(para,callbackobject.userdata);
            if(ret === false){
                return
            }
            delete sdk.callbacks[para.callbackname];
        }
    }
};

sdk.post = function(cmd,para){
    if(sdk.os.isIOS){
        var message = {};
        message.meta = {
            cmd:cmd
        };
        message.para = para || {};
        window.webview.post(message);
    }else if(window.sdk.os.isAndroid){
        window.webview.post(cmd,JSON.stringify(para));
    }
};
sdk.postWithCallback = function(cmd,para,callback,ud){
    var callbackname = sdk.callbackname();
    sdk.addCallback(callbackname,callback,ud);
    if(sdk.os.isIOS){
        var message = {};
        message.meta  = {
            cmd:cmd,
            callback:callbackname
        };
        message.para = para;
        window.webview.post(message);
    }else if(window.sdk.os.isAndroid){
        para.callback = callbackname;
        window.webview.post(cmd,JSON.stringify(para));
    }
};
sdk.dispatchEvent = function(para){
    if (!para) {
        para = {"name":"webviewLoadComplete"};
    }
    var evt = {};
    try {
        evt = new Event(para.name);
        evt.para = para.para;
    } catch(e) {
        evt = document.createEvent("HTMLEvents");
        evt.initEvent(para.name, false, false);
    }
    window.dispatchEvent(evt);
};
sdk.addEventListener = window.addEventListener;

sdk.testFun = function(){
    try{
        window.sdk.post("verifySubmitSuccess",{});
    } catch (e) {
        console.log(e);
    }
};
sdk.stringify = function(obj){
    var type = typeof obj;
    if (type == "object"){
        return JSON.stringify(obj);
    }else {
        return obj;
    }
};
sdk.nativecallback = function(obj){
    if(sdk.os.isIOS){
        return sdk.stringify(obj.data);
    }else if(window.sdk.os.isAndroid){
        window.webview.post(obj.callback,sdk.stringify(obj));
    }
};

sdk.http = function(envcmd,options){
    var para = {
        url:options.url,
        type:options.type || "get",
        timeout:options.timeout || 60000, // 60 second
        data:options.data,
        contentType:options.contentType,
        responseType:options.responseType,
        headers:options.headers
    }
    var ud = {
        envcmd:envcmd,
        para:para,
        callbacks:{
            success:options.success,
            complete:options.complete,
            beforeSend:options.beforeSend,
            error:options.error
        }
    }
    ud.callbacks.beforeSend ? ud.callbacks.beforeSend() : ""
    sdk.postWithCallback(envcmd,para,function(para,ud){
        if (para.success){
            ud.callbacks.success ? ud.callbacks.success(para.data) : ""
        }else{
            ud.callbacks.error ? ud.callbacks.error(null,para.errorReason) : ""
        }
        ud.callbacks.complete ? ud.callbacks.complete() : ""
    },ud)
}

sdk.sdkApi = function(options){
    return sdk.http("sdk_api",options)
}
sdk.studioApi = function(options){
    return sdk.http("studioapi",options)
}
sdk.parseQuery = function(query){
    var reg = /([^=&\s]+)[=\s]*([^=&\s]*)/g;
    var obj = {};
    while(reg.exec(query)){
        obj[RegExp.$1] = RegExp.$2;
    }
    return obj;
}
sdk.parseUrl = function(url){
  var a = document.createElement('a')
  a.href = url
  var querys = sdk.parseQuery((a.search || "").replace("?",""))
  return { protocol:a.protocol || "",host:a.host || "",querys:querys,path:a.pathname || "" }
}
sdk.newPage = function(paras){
  var url = paras.url
  if (url == undefined || url == ""){
    return
  }
  var urlinfo = sdk.parseUrl(url)
  if(urlinfo.protocol == "dajia:"){
    var openPageParas = {
      object_title:paras.title,
      object_type:urlinfo.querys.objectType,
      object_info:urlinfo.querys
    }
    var messageParas = {
      title:paras.title,
      content:"",
      action:"OPEN_APP_INTERNAL_PAGE",
      extra:openPageParas,
        needCloseSelf: paras.needCloseSelf,
      url:paras.url
    }
    sdk.post("newPage",messageParas)
  }else{
    sdk.post("newPage",paras)
  }
}


/**
 * 跳转至新的web页面
 */
sdk.newWebPage = function(url, title, needCloseSelf) {
    needCloseSelf = !!needCloseSelf ? needCloseSelf : false;
    try {
        sdk.newPage({
            title: title,
            url: url,
            needCloseSelf: needCloseSelf
        });
    } catch (e) {
        document.location = url;
    }
};


sdk.toast = function(msg) {
  sdk.post("showToast",{message:msg})
}
sdk.alert = function(para) {
    var title = para.title || ""
    var content = para.content || ""
    sdk.postWithCallback("showDialog",{title:title,content:content,buttons:[{title:"知道了"}]},function(paras){})
}
sdk.getSelectionText = function(){
    return sdk.nativecallback({callback:"select_text",data:document.getSelection().toString()});
};
sdk.reportAnalysisEvent = function(id, args) {
    try {
        args = !!args ? args : {};
        sdk.post("reportAnalysisEvent",{id: id, paras: args});
    } catch (e) {
        console.log("统计失败");
    }
};

window.sdk = sdk;
