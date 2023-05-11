(ns bm.nace-finder.layout.footer
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [bm.web-components.utils :refer
             (BMElement tag append-child register-custom-element)]))

(defclass Footer
  (extends BMElement)

  (constructor [this]
               (super)
               (append-child this (tag :style (inline "./footer.css")))
               (append-child this (tag :footer (tag :div "2023")))))

(register-custom-element :bm-footer Footer)
