package com.example.a15minutetimer;

import com.airbnb.epoxy.EpoxyDataBindingPattern;
//modelから始まるxmlファイルをEpoxyの監視下にする
@EpoxyDataBindingPattern(rClass = R.class, layoutPrefix = "model")
interface EpoxyConfig{
}
