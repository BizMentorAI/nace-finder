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
               (append-child this (tag :style (inline "./layout.css")))
               (append-child this (tag :bm-header))
               (append-child this (tag :main "<slot></slot>"))
               (append-child this (tag :bm-footer))))

(register-custom-element :bm-layout Layout)
