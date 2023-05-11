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

                 (.screenDebugInfo this dbg)
                 (.addEventListener js/window
                                    "resize"
                                    (fn [event]
                                      (js/console.log "Window resized.")
                                      (.screenDebugInfo this dbg)))

                 (when (.isMobileSafari this)
                   ;; Fix page height for mobile Safari.
                   ;; Doing this in CSS doesn't work as Chrome matches it also.
                   ;; https://allthingssmitty.com/2020/05/11/css-fix-for-100vh-in-mobile-webkit
                   (js* "this.style.minHeight = '-webkit-fill-available'"))))

  Object
  (isMobileSafari [this]
                  (and
                   (js/window.navigator.userAgent.match #"(?i)iphone|ipad")
                   (js/window.navigator.userAgent.match #"(?i)WebKit")
                   (not (js/window.navigator.userAgent.match #"(?i)CriOS"))))

  (screenDebugInfo [this div]
                   (.isMobileSafari this)
                   (set! div -innerHTML
                         (str (when (.isMobileSafari this) "(i) ")
                              (cond
                                (.-matches (js/window.matchMedia "(max-width: 600px)")) "XS "
                                (.-matches (js/window.matchMedia "(max-width: 900px)")) "SM "
                                (.-matches (js/window.matchMedia "(max-width: 1200px)")) "LG ")
                              js/window.innerWidth "x" js/window.innerHeight
                              " (" js/window.devicePixelRatio ")"))))

(register-custom-element :bm-layout Layout)

;; /* XS retina */
;; @media (-webkit-min-device-pixel-ratio: 2) (min-resolution: 192dpi) and (max-width: 1000px) {
;;   body { background: "red"; }
;;   #screen::after { content: "XS(r)"; }
;; }

;; window.matchMedia("(max-width: 700px)")
;; /* XS */
;; @media (max-width: 650px) {
;;   #screen::after { content: "XS"; }
;; }

;; /* SM */
;; @media (max-width: 900px) {
;;   #screen::after { content: "SM"; }
;; }

;; /* LG */
;; @media (min-width: 1200px) {
;;   #screen::after { content: "LG"; }
;; }
