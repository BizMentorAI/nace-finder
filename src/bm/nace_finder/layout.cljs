(ns bm.nace-finder.layout
  (:require [shadow.cljs.modern :refer (defclass)]
            [bm.nace-finder.layout.header]
            [bm.nace-finder.layout.footer]))

(def styles "
  html {
    width: 100%;
    height: 100%;
  }

  body {
    margin: 0;
    background: yellow;
  }

  bm-header {
    grid-area: header;
    color: white;
  }

  /* TODO: grid-area main, we need to wrap the original children in <main></main> */

  bm-footer {
    grid-area: footer;
    color: purple;
  }
")


; We don't want to use Shadow DOM, so we can fuck with the overall
; document in order to set up the layout styles properly.
; The downside is that the slots are not working and which is why
; we are using the work-around below.

(defclass Layout
  (extends js/HTMLElement)

  (constructor [this]
               (super)

               ; We'd be able to do this would have have shadow DOM.
               ; Maybe worth breaking this into 2 components, one without shadow DOM and other with it?
               ; Or even just putting the body things into the global CSS?
               (js* "this.style.display = 'grid'") ; FIXME: Can I do it via CSS? -> :host (with shadow DOM)
               (js* "this.style.height = '100vh'")
               (js* "this.style.gridTemplateRows = 'auto 1fr auto'")
               (js* "this.style.gridTemplateAreas = \"'header' 'main' 'footer'\"")

               (let [original-children (. this -childNodes)]
                 (let [style (js/document.createElement "style")]
                   (set! style -innerText styles)
                   (.prepend this style))

                 (.prepend this (js/document.createElement "bm-header"))
                                        ; Here will be the original children.
                 (.appendChild this (js/document.createElement "bm-footer"))))

  Object
  (connectedCallback [this]
                     (js/console.log "bm-layout" this)))

(when-not (js/window.customElements.get "bm-layout")
  (js/window.customElements.define "bm-layout" Layout))
