/*
 * cp SDK
 */
(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        define([], function () {
            return factory(root);
        });
    } else if (typeof module === 'object' && typeof module.exports === 'object') {
        module.exports = factory(root);
    } else {
        window.GameSDK = factory(root);
    }
})(
    typeof global !== 'undefined'
        ? global
        : typeof window !== 'undefined'
        ? window
        : this,
    function (window) {
        function _parseSearch() {
            var search = location.search;
            if (search.length > 1) {
                search = search.slice(1);
            }

            var kvArray = search.split('&');
            var kvObj = {};
            kvArray.forEach(function (kv, index) {
                var a = kv.split('=');
                kvObj[a[0]] = decodeURIComponent(a[1]);
            });

            return kvObj;
        }

        function isNativePlatform () {
            var ua = navigator.userAgent.toLowerCase();
            if (ua.match(/cocosplay/i) == "cocosplay") {
                return true;
            }
            return false;
        };

        function _isH5() {
            var isH5 = _parseSearch().isH5;
            console.log('isNativePlatform ' + isNativePlatform())
            if (typeof isH5 === 'undefined' && isNativePlatform()) {
                return false;
            }
            return true;
        }

        function receiveMessage(event) {
            if (event.origin !== parentHost) return;
            var payload;
            try {
                payload = JSON.parse(event.data);
            } catch (e) {
                return;
            }
            var params;
            switch (payload.msg) {
                // 监听支付结果
                case 'onpay':
                    params = payload.params;
                    GameSDK.gameCenterCallback('onPay', params);
                    break;
                // 监听登录结果
                case 'onlogin':
                    params = payload.params;
                    GameSDK.gameCenterCallback('onInit', params);
                    break;
                // 监听广告参数获取结果
                case 'onAdRelatedParams':
                    params = payload.params;
                    GameSDK.gameCenterCallback('onAdParams', params);
                    break;
            }
        }

        var isH5 = _isH5();
        if (isH5) {
            var parentWin = window.parent;
            var parentHost = _parseSearch().host || '*';
            window.addEventListener('message', receiveMessage);
        }

        var GameSDK = {
            // CallBack
            callBacks: {},
            // Internal -- Call Native
            callNative: function (cmd, param) {
                param = param == null ? '' : JSON.stringify(param);
                var str = 'js://callNative?cmd=' + cmd + '&param=' + param;
                return prompt(str);
            },

            nativeCallback: function (cmd, param) {
                var func = this.callBacks[cmd];
                if (func) {
                    func(JSON.parse(param));
                }
            },

            registerCallback: function (cmd, func) {
                this.callBacks[cmd] = func;
            },

            // 设置回调函数
            setOnInitCB: function (func) {
                this.registerCallback('onInit', func);
            },

            setOnRoomInfoCB: function (func) {
                this.registerCallback('onRoomInfo', func);
            },

            setOnReadyCB: function (func) {
                this.registerCallback('onReady', func);
            },

            setOnStartCB: function (func) {
                this.registerCallback('onStart', func);
            },

            setOnMessageCB: function (func) {
                this.registerCallback('onMessage', func);
            },

            setOnFinishCB: function (func) {
                this.registerCallback('onFinish', func);
            },

            setOnAudioCB: function (func) {
                this.registerCallback('onAudio', func);
            },

            setOnResumeCB: function (func) {
                this.registerCallback('onResume', func);
            },

            setOnPauseCB: function (func) {
                this.registerCallback('onPause', func);
            },

            setOnPayCB: function (func) {
                this.registerCallback('onPay', func);
            },
            setOnAdParamsCB: function (func) {
                this.registerCallback('onAdParams', func);
            },
            // 退出游戏
            // 参数:
            //   reason: int 退出原因: 1 - 正常退出，2-异常退出
            quit: function (reason) {
                var param = {reason: reason};
                if (isH5) {
                } else {
                    this.callNative('quit', param);
                }
            },

            // 获取游戏房间信息
            getRoomInfo: function () {
                if (isH5) {
                } else {
                    this.callNative('getRoomInfo');
                }
            },

            // 游戏终止
            // 参数:
            //   result: int 游戏结果: 1、胜，2、负，3、平
            finish: function (result) {
                var param = {result: result};
                if (isH5) {
                } else {
                    this.callNative('finish', param);
                }
            },
            // 获取channelId和gameId
            getAdRelatedParams: function () {
                if (isH5) {
                  parentWin.postMessage(
                    JSON.stringify({ msg: 'adRelatedParams' }),
                    parentHost
                  );
                } else {
                    console.log('Native: getAdRelatedParams')
                    this.callNative('getAdRelatedParams');
                }
            },

            // 设置屏幕朝向
            // 参数:
            //    orientation: int 屏幕朝向: 0、横屏，1、竖屏
            setOrientation: function (orientation) {
                var param = {orientation: orientation};
                if (isH5) {
                    parentWin.postMessage(
                        JSON.stringify({msg: 'orientation', params: param}),
                        parentHost
                    );
                } else {
                    this.callNative('setOrientation', param);
                }
            },

            // 设置声音
            // 参数:
            //   enable: int 是否开启: 0、关闭，1、开启
            //   volume: int 音量
            setAudio: function (enable, volume) {
                var param = {
                    enable: enable,
                    volume: volume
                };
                if (isH5) {
                } else {
                    this.callNative('setAudio', param);
                }
            },

            // 设置麦克风
            // 参数:
            //   enable: int 是否开启: 0、关闭，1、开启
            //   volume: int 音量: 0 ~ 100
            setMic: function (enable, volume) {
                var param = {
                    enable: enable,
                    volume: volume
                };
                if (isH5) {
                } else {
                    this.callNative('setMic', param);
                }
            },

            // 设置游戏加载进度（SDK版本 >= 2）
            // 参数:
            //    progress: int 加载进度: 0 ~ 100
            // 说明: 从SDK版本2开始，平台增加了统一的游戏加载进度界面，用于游戏后台加载时显示。
            //      游戏需要在初始化后，通过此函数报告游戏加载进度。加载界面将显示“加载中...”
            //      当进度>=100%时，加载界面并不会关闭，加载界面将提醒用户“等待对手进入中...”
            //      因此，游戏需要在对手都进入房间后，调用hideLoadProgress函数关闭加载界面。
            setLoadProgress: function (progress) {
                var param = {progress: progress};
                if (isH5) {
                } else {
                    this.callNative('setLoadProgress', param);
                }
            },

            // 隐藏游戏加载进度（SDK版本 >= 2）
            // 参数:
            //    无
            // 说明: 用于关闭加载进度界面。此后玩家才可以和游戏交互。
            hideLoadProgress: function () {
                var param = {};
                if (isH5) {
                    parentWin.postMessage(
                        JSON.stringify({msg: 'hideLoadProgress'}),
                        parentHost
                    );
                } else {
                    this.callNative('hideLoadProgress', param);
                }
            },

            // 游戏准备
            // 参数
            //   userData: string 用户数据
            ready: function (userData) {
                var param = {userData: userData};
                if (isH5) {
                } else {
                    this.callNative('ready', param);
                }
            },

            // 游戏消息广播
            // 参数
            //  message: string 广播的消息
            //  includeMe: int 是否也广播给自己: 0、不包含，1、包含
            broadcast: function (message, includeMe) {
                if (includeMe == null) {
                    includeMe = 0;
                }
                var param = {
                    message: message,
                    includeMe: includeMe
                };
                if (isH5) {
                } else {
                    this.callNative('broadcast', param);
                }
            },

            // 游戏结束
            // 参数
            //   result: int 游戏结果: 1、胜，2、负，3、平
            gameOver: function (result) {
                var param = {result: result};
                if (isH5) {
                } else {
                    this.callNative('gameOver', param);
                }
            },
            gameCenterCallback: function (cmd, params) {
                var func = this.callBacks[cmd];
                if (func) {
                    func(params);
                }
            },
            // 初始化SDK
            // 参数:
            //   gameId: int 游戏ID
            init: function ( gameId) {
                var params = {
                    gameId: gameId
                };
                if (isH5) {
                    _init('init', params);
                } else {
                    this.callNative('init', params);
                }
            },
            // 游戏支付
            // 参数
            //   orderId: int 订单号
            //   goodsName: string 商品名称
            //   goodsDesc: string 商品描述
            //   orderAmount: int 订单金额
            //   extension： string 透传参数
            //   notifyURL: string 支付付款通知地址
            pay: function (
                orderId,
                goodsName,
                goodsDesc,
                orderAmount,
                extension,
                notifyURL
            ) {
                var params = {
                    orderId: orderId,
                    goodsName: goodsName,
                    goodsDesc: goodsDesc,
                    orderAmount: orderAmount,
                    extension: extension,
                    notifyURL: notifyURL
                };
                if (isH5) {
                    parentWin.postMessage(
                        JSON.stringify({msg: 'pay', params: params}),
                        parentHost
                    );
                } else {
                    this.callNative('pay', params);
                }
            }
        };
        var _init = function (cmd, params) {
            parentWin.postMessage(
                JSON.stringify({msg: 'init', params: params}),
                parentHost
            );
        };
        return GameSDK;
    }
);
