window.__require = function e(t, n, r) {
  function s(o, u) {
    if (!n[o]) {
      if (!t[o]) {
        var b = o.split("/");
        b = b[b.length - 1];
        if (!t[b]) {
          var a = "function" == typeof __require && __require;
          if (!u && a) return a(b, !0);
          if (i) return i(b, !0);
          throw new Error("Cannot find module '" + o + "'");
        }
        o = b;
      }
      var f = n[o] = {
        exports: {}
      };
      t[o][0].call(f.exports, function(e) {
        var n = t[o][1][e];
        return s(n || e);
      }, f, f.exports, e, t, n, r);
    }
    return n[o].exports;
  }
  var i = "function" == typeof __require && __require;
  for (var o = 0; o < r.length; o++) s(r[o]);
  return s;
}({
  CallLocal: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "4ca32ruudhK3pTlbisrO3Zf", "CallLocal");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var UserData_1 = require("./UserData");
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var CallLocal = function(_super) {
      __extends(CallLocal, _super);
      function CallLocal() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.label = null;
        return _this;
      }
      CallLocal.prototype.start = function() {
        this.runtimeInfo();
        var info1 = this.callNative();
        this.label.string += "\n" + info1;
        var webUser = new UserData_1.default();
        this.label.string += "\n\u7f51\u9875\u7684\u7528\u6237 " + webUser.num + ", " + webUser.enable;
        var anUser = this.getOneObject();
        this.label.string += "\ndata from android: " + anUser.num + ", " + anUser.enable;
      };
      CallLocal.prototype.runtimeInfo = function() {
        var info = "cc.sys.isNative: " + cc.sys.isNative + " ; cc.sys.isMobile: " + cc.sys.isMobile;
        info += "\ncc.sys.os:         " + cc.sys.os;
        info += "\ncc.sys.platform:   " + cc.sys.platform;
        info += "\ncc.sys.ANDROID:    " + cc.sys.ANDROID;
        info += "\ncc.sys.OS_IOS:     " + cc.sys.OS_IOS;
        info += "\ncc.sys.OS_ANDROID: " + cc.sys.OS_ANDROID;
        this.label.string = info;
      };
      CallLocal.prototype.callNative = function() {
        var result = window.androidInterface.getStringInfo();
        return result;
      };
      CallLocal.prototype.getOneObject = function() {
        var json = window.androidInterface.getOneJson();
        var user = JSON.parse(json);
        return user;
      };
      __decorate([ property(cc.Label) ], CallLocal.prototype, "label", void 0);
      CallLocal = __decorate([ ccclass ], CallLocal);
      return CallLocal;
    }(cc.Component);
    exports.default = CallLocal;
    cc._RF.pop();
  }, {
    "./UserData": "UserData"
  } ],
  DragControl: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "3ebc8/dMYFFHpMptDZ8hs9H", "DragControl");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var DragToAnywhere_1 = require("./DragToAnywhere");
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var DragControl = function(_super) {
      __extends(DragControl, _super);
      function DragControl() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.drag_item = null;
        return _this;
      }
      DragControl.prototype.start = function() {
        this._addStar1();
      };
      DragControl.prototype._addStar1 = function() {
        var node1 = cc.instantiate(this.drag_item);
        this.node.addChild(node1);
        node1.x = 100;
        node1.y = 100;
        node1.getComponent(DragToAnywhere_1.default).label.string = "\u6c34\u661f";
      };
      __decorate([ property(cc.Prefab) ], DragControl.prototype, "drag_item", void 0);
      DragControl = __decorate([ ccclass ], DragControl);
      return DragControl;
    }(cc.Component);
    exports.default = DragControl;
    cc._RF.pop();
  }, {
    "./DragToAnywhere": "DragToAnywhere"
  } ],
  DragToAnywhere: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "e62efYIUYNAhY9cz23x+5bB", "DragToAnywhere");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var DragToAnywhere = function(_super) {
      __extends(DragToAnywhere, _super);
      function DragToAnywhere() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.label = null;
        return _this;
      }
      DragToAnywhere.prototype.start = function() {};
      DragToAnywhere.prototype.onEnable = function() {
        this.node.on(cc.Node.EventType.TOUCH_MOVE, this._onTouchMove, this);
        this.node.on(cc.Node.EventType.TOUCH_END, this._onTouchEnd, this);
      };
      DragToAnywhere.prototype.onDisable = function() {
        this.node.off(cc.Node.EventType.TOUCH_MOVE, this._onTouchMove, this);
        this.node.off(cc.Node.EventType.TOUCH_END, this._onTouchEnd, this);
      };
      DragToAnywhere.prototype._onTouchMove = function(touchEvent) {
        var location = touchEvent.getLocation();
        this.node.position = this.node.parent.convertToNodeSpaceAR(location);
      };
      DragToAnywhere.prototype._onTouchEnd = function(touchEvent) {
        cc.log(this.label.string, "position", this.node.position);
        cc.log(this.label.string, "this.node.convertToWorldSpaceAR(cc.Vec2.ZERO)", this.node.convertToWorldSpaceAR(cc.Vec2.ZERO));
      };
      __decorate([ property(cc.Label) ], DragToAnywhere.prototype, "label", void 0);
      DragToAnywhere = __decorate([ ccclass ], DragToAnywhere);
      return DragToAnywhere;
    }(cc.Component);
    exports.default = DragToAnywhere;
    cc._RF.pop();
  }, {} ],
  DragToControl: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "06e54dgBZpMUqneJElItBEp", "DragToControl");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var DragToTarget_1 = require("./DragToTarget");
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var DragToControl = function(_super) {
      __extends(DragToControl, _super);
      function DragToControl() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.drag_to_item = null;
        _this.dragTargets = [];
        _this.itemNum = 1;
        return _this;
      }
      DragToControl.prototype.start = function() {
        this.createItem();
      };
      DragToControl.prototype.createItem = function() {
        var d = cc.instantiate(this.drag_to_item);
        this.node.addChild(d);
        var dragTo = d.getComponent(DragToTarget_1.default);
        dragTo.targetOfDragList = this.dragTargets;
        dragTo.nameLabel.string = "" + this.itemNum++;
      };
      __decorate([ property(cc.Prefab) ], DragToControl.prototype, "drag_to_item", void 0);
      __decorate([ property(cc.Node) ], DragToControl.prototype, "dragTargets", void 0);
      DragToControl = __decorate([ ccclass ], DragToControl);
      return DragToControl;
    }(cc.Component);
    exports.default = DragToControl;
    cc._RF.pop();
  }, {
    "./DragToTarget": "DragToTarget"
  } ],
  DragToTarget: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "34c96S4pNJLZZOaO1txTFC0", "DragToTarget");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var DragToTarget = function(_super) {
      __extends(DragToTarget, _super);
      function DragToTarget() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.nameLabel = null;
        _this.targetOfDragList = [];
        _this._oldPos = null;
        return _this;
      }
      DragToTarget.prototype.start = function() {
        this._oldPos = this.node.position;
      };
      DragToTarget.prototype.onEnable = function() {
        this.node.on(cc.Node.EventType.TOUCH_MOVE, this._onTouchMove, this);
        this.node.on(cc.Node.EventType.TOUCH_END, this._onTouchEnd, this);
      };
      DragToTarget.prototype.onDisable = function() {
        this.node.off(cc.Node.EventType.TOUCH_MOVE, this._onTouchMove, this);
        this.node.off(cc.Node.EventType.TOUCH_END, this._onTouchEnd, this);
      };
      DragToTarget.prototype._onTouchMove = function(touchEvent) {
        var location = touchEvent.getLocation();
        this.node.position = this.node.parent.convertToNodeSpaceAR(location);
      };
      DragToTarget.prototype._onTouchEnd = function(touchEvent) {
        if (0 === this.targetOfDragList.length) return;
        var inTarget = false;
        for (var _i = 0, _a = this.targetOfDragList; _i < _a.length; _i++) {
          var targetNode = _a[_i];
          if (this._withinTarget(targetNode, touchEvent)) {
            inTarget = true;
            break;
          }
        }
        inTarget || (this.node.position = this._oldPos);
      };
      DragToTarget.prototype._withinTarget = function(targetNode, touchEvent) {
        var rect = targetNode.getBoundingBox();
        var location = touchEvent.getLocation();
        var point = targetNode.parent.convertToNodeSpaceAR(location);
        return rect.contains(point);
      };
      __decorate([ property(cc.Label) ], DragToTarget.prototype, "nameLabel", void 0);
      __decorate([ property(cc.Node) ], DragToTarget.prototype, "targetOfDragList", void 0);
      DragToTarget = __decorate([ ccclass ], DragToTarget);
      return DragToTarget;
    }(cc.Component);
    exports.default = DragToTarget;
    cc._RF.pop();
  }, {} ],
  Network1: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "c6c96ORc+hNqYUbGb/jMcLi", "Network1");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var Network1 = function(_super) {
      __extends(Network1, _super);
      function Network1() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.label = null;
        return _this;
      }
      Network1.prototype.start = function() {};
      Network1.prototype.clickGet = function() {
        this.httpGet1();
      };
      Network1.prototype.httpGet1 = function() {
        var url = "https://www.mxnzp.com/api/ip/self";
        var xhr = new XMLHttpRequest();
        var label = this.label;
        xhr.onreadystatechange = function() {
          if (4 === xhr.readyState) {
            cc.log(xhr.responseText);
            label.string = xhr.responseText;
          }
        };
        xhr.open("GET", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("app_id", "nldmuckoppsvvtzv");
        xhr.setRequestHeader("app_secret", "bTZ6QmhXa3ZxRmpIZTRNYTBjUlpPdz09");
        xhr.send();
      };
      Network1.prototype.httpPost = function() {
        cc.log("\u53d1\u8d77\u8bf7\u6c42");
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
          if (4 == xhr.readyState && xhr.status >= 200 && xhr.status < 400) {
            var response = xhr.responseText;
            cc.log(response);
            var respObj = JSON.parse(response);
            cc.log(respObj);
          }
        };
        var url = "http://intgo.cn:3004/launcherUpdate";
        var params = {
          userName: "hngz244"
        };
        cc.log("url", url);
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify(params));
      };
      __decorate([ property(cc.Label) ], Network1.prototype, "label", void 0);
      Network1 = __decorate([ ccclass ], Network1);
      return Network1;
    }(cc.Component);
    exports.default = Network1;
    cc._RF.pop();
  }, {} ],
  PositionDemo: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "fe6adsN2ERCyJp20spBqoaB", "PositionDemo");
    "use strict";
    var __extends = this && this.__extends || function() {
      var extendStatics = function(d, b) {
        extendStatics = Object.setPrototypeOf || {
          __proto__: []
        } instanceof Array && function(d, b) {
          d.__proto__ = b;
        } || function(d, b) {
          for (var p in b) b.hasOwnProperty(p) && (d[p] = b[p]);
        };
        return extendStatics(d, b);
      };
      return function(d, b) {
        extendStatics(d, b);
        function __() {
          this.constructor = d;
        }
        d.prototype = null === b ? Object.create(b) : (__.prototype = b.prototype, new __());
      };
    }();
    var __decorate = this && this.__decorate || function(decorators, target, key, desc) {
      var c = arguments.length, r = c < 3 ? target : null === desc ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
      if ("object" === typeof Reflect && "function" === typeof Reflect.decorate) r = Reflect.decorate(decorators, target, key, desc); else for (var i = decorators.length - 1; i >= 0; i--) (d = decorators[i]) && (r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r);
      return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var _a = cc._decorator, ccclass = _a.ccclass, property = _a.property;
    var NewClass = function(_super) {
      __extends(NewClass, _super);
      function NewClass() {
        var _this = null !== _super && _super.apply(this, arguments) || this;
        _this.nodes = [];
        return _this;
      }
      NewClass.prototype.start = function() {};
      NewClass.prototype.update = function(dt) {
        this._getPos();
      };
      NewClass.prototype._getPos = function() {
        for (var _i = 0, _a = this.nodes; _i < _a.length; _i++) {
          var child = _a[_i];
          if (null == child) continue;
          this._logPosInfo(child);
          cc.log("----------------------");
        }
      };
      NewClass.prototype._logPosInfo = function(node) {
        var name = node.name;
        var postion = node.position;
        cc.log(name, "position (", postion.x.toFixed(2), ",", postion.y.toFixed(2), ")");
        var worldPos = node.parent.convertToWorldSpaceAR(node.position);
        cc.log(name, "\u4e16\u754c\u5750\u6807(", worldPos.x.toFixed(2), ",", worldPos.y.toFixed(2), ")");
        var label = node.getComponent(cc.Label);
        label && (label.string = "p(" + postion.x.toFixed(2) + ", " + postion.y.toFixed(2) + ")\nw(" + worldPos.x.toFixed(2) + ", " + worldPos.y.toFixed(2) + ")");
      };
      __decorate([ property(cc.Node) ], NewClass.prototype, "nodes", void 0);
      NewClass = __decorate([ ccclass ], NewClass);
      return NewClass;
    }(cc.Component);
    exports.default = NewClass;
    cc._RF.pop();
  }, {} ],
  UserData: [ function(require, module, exports) {
    "use strict";
    cc._RF.push(module, "4507aXOGN1EWbi7f4RvCfor", "UserData");
    "use strict";
    Object.defineProperty(exports, "__esModule", {
      value: true
    });
    var User = function() {
      function User() {
        this.num = -1;
        this.enable = false;
        this.rule = "none";
        this.starCount = 3;
      }
      return User;
    }();
    exports.default = User;
    cc._RF.pop();
  }, {} ]
}, {}, [ "DragControl", "DragToAnywhere", "DragToControl", "DragToTarget", "Network1", "CallLocal", "UserData", "PositionDemo" ]);