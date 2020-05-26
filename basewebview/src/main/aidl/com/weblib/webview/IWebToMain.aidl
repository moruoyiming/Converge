package com.weblib.webview;

import com.weblib.webview.ICallbackFromMainToWeb;

interface IWebToMain {
      void handleWebAction(String actionName, String jsonParams, in ICallbackFromMainToWeb callback);
}
