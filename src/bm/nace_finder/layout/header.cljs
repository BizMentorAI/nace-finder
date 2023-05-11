(ns bm.nace-finder.layout.header
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [bm.web-components.utils :refer
             (BMElement tag append-child register-custom-element)]))

(defclass Header
  (extends BMElement)

  (constructor [this]
               (super)
               (append-child this (tag :style (inline "./header.css")))
               (append-child this (tag :h1 "NACE lookup"))
               (append-child this (tag :ul "<li>fast</li><li>easy</li><li>accurate</li>"))))

(register-custom-element :bm-header Header)
