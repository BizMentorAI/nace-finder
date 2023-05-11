(ns bm.nace-finder.layout
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [bm.web-components.utils :refer
             (BMElement tag append-child register-custom-element)]
            [bm.nace-finder.layout.header]
            [bm.nace-finder.layout.footer]))

(defclass Layout
  (extends BMElement)

  (constructor [this]
               (super)
               (let [dbg (tag :div {:id "screen"})]
                 (append-child this dbg)
                 (append-child this (tag :style (inline "./layout.css")))
                 (append-child this (tag :bm-header))
                 (append-child this (tag :main (tag :slot)))
                 (append-child this (tag :bm-footer))

                 (when (.isMobileSafari this)
                   (js/console.log "Mobile Safari!")
                   (set! dbg -innerHTML "i")
                   ;; Fix page height for mobile Safari.
                   ;; Doing this in CSS doesn't work as Chrome matches it also.
                   ;; https://allthingssmitty.com/2020/05/11/css-fix-for-100vh-in-mobile-webkit
                   (js* "this.css.minHeight = '-webkit-fill-available'"))))

  Object
  (isMobileSafari [this]
                  (and
                   (js/window.navigator.userAgent.match #"(?i)iphone|ipad")
                   (js/window.navigator.userAgent.match #"(?i)WebKit")
                   (not (js/window.navigator.userAgent.match #"(?i)CriOS")))))

(register-custom-element :bm-layout Layout)
