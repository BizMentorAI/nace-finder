(ns bm.nace-finder.nace-finder
  (:require [shadow.cljs.modern :refer (defclass)]
            [shadow.resource :refer (inline)]
            [bm.web-components.utils :refer
             (BMElement tag append-child register-custom-element)]
            [bm.components.autocomplete]))

(defclass NaceFinder
  (extends BMElement)

  (constructor [this]
               (super)
               (append-child this (tag :style (inline "./nace_finder.css")))
               ;(append-child this (tag :p "NACE Codes are a pan-European classification system that groups organisations according to the general nature of their business activities. They are used for statistical purposes by the Central Statistics Office <a href='https://www.cso.ie'>www.cso.ie</a> and the EU."))
               (append-child this (tag :h4 "Instantly identify the NACE and CPA codes you require:"))
               (append-child this (tag :bm-autocomplete))))

(register-custom-element :bm-nace-finder NaceFinder)
