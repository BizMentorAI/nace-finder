(ns bm.nace-finder.layout
  (:require [shadow.cljs.modern :refer (defclass)]
            [bm.nace-finder.layout.header]
            [bm.nace-finder.layout.footer]))

(def styles "
  :host {
    display: grid;
    height: 100vh;
    grid-template-rows: auto 1fr auto;
    grid-template-areas:
      \"header\"
      \"main\"
      \"footer\";
  }

  bm-header {
    grid-area: header;
    background: red;
    color: white;
  }

  main {
    grid-area: main;
  }

  bm-footer {
    grid-area: footer;
    background: red;
    color: white;
  }
")

(defclass Layout
  (extends js/HTMLElement)

  (constructor [this]
               (super)

               (let [shadow (.attachShadow this #js {:mode "open"})]
                 (set! shadow -innerHTML
                       (str
                        "<style>" styles "</style>"
                        "<bm-header></bm-header>"
                        "<main><slot></slot></main>"
                        "<bm-footer></bm-footer>"))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-layout" this)))

(when-not (js/window.customElements.get "bm-layout")
  (js/window.customElements.define "bm-layout" Layout))
