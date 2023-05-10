(ns bm.nace-finder.layout
  (:require [shadow.cljs.modern :refer (defclass)]))

(defclass Layout
  (extends js/HTMLElement)

  (constructor [this]
               (super)
               (let [shadow (.attachShadow this #js {:mode "open"})]
                 (set! shadow -innerHTML "<slot></slot>")))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-layout" this)))

(when-not (js/window.customElements.get "bm-layout")
  (js/window.customElements.define "bm-layout" Layout))

;; const shadow = this.attachShadow({mode: 'open'})

;;   shadow.innerHTML = `
;;   <style>
;;     html {
;;       width: 100%;
;;       height: 100%;
;;     }

;;     body {
;;       margin: 0;
;;     }
;;   </style>

;;   <site-header></site-header>
;;   <slot></slot>
