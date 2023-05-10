(ns bm.nace-finder
  "This is the sole JS entrypoint for the app."
  (:require [bm.nace-finder.layout]
            [bm.nace-finder.nace-finder]))

(defn ^:dev/after-load render
  "Render bm-web and rerender it after each change." []
  ;(js/console.clear)
  ;(.render root (r/as-element [current-page]))
  )

; FIXME: We don't need export or do we? With ESM.
(defn ^:export main
  "Render the whole app." []
  (render))
