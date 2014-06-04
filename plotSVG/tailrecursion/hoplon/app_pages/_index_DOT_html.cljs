(ns tailrecursion.hoplon.app-pages._index_DOT_html (:require [chart :as c] [tailrecursion.hoplon.reload :refer [reload-all]] [tailrecursion.javelin :refer [alts! cell-map propagate! next-rank deref* lift cell input? cell-doseq* bf-seq destroy-cell! last-rank set-cell! set-formula! cell?]] [tailrecursion.hoplon :refer [script do! track article noscript command h4 h3 mark basefont h5 span input h2 th label h6 pre nav vector?* address sup h1 table font option datalist u safe-nth on! footer select q samp source summary li p td noframes node? iframe rel tr s *initfns* add-attributes! colgroup relx html dfn optgroup tbody text-val! ul hgroup sub strong data progress loop-tpl* acronym append-child replace-children! img details fieldset html-head em html-time rt when-dom video keygen div val-id dt ol link init form is-ie8 check-val! menu timeout del a parse-args area legend hr dir header param meter tfoot blockquote eventsource b dl figcaption caption route-cell style rel-event abbr ruby applet html-meta bdi embed rp figure on-append! canvas section object strike title button output audio initialized? add-children! dd bdo cite code kbd big seq?* frame rely col tt i ins thead unsplice isindex frameset center spliced base $text by-id $comment br textarea wbr html-map small add-initfn! html-body aside html-var]]) (:require-macros [tailrecursion.javelin :refer [cell-doseq cell= defc defc= mx with-let prop-cell mx2 cell-let set-cell!= macroexpand-all]] [tailrecursion.hoplon :refer [with-init! body text defelem loop-tpl head with-timeout with-interval def-values flatten-expr]]))

(clojure.core/defn ^:export hoploninit [] (reload-all) (def src-url "https://github.com/tailrecursion/hoplon-demos/blob/master/plotSVG/src/index.cljs.hl") (def c1 "#006666") (def c2 "#660066") (defn data! [] (vec (for [x (range 0 11)] [x (rand-int 11)]))) (defn add! [data] (conj data [(-> data last first inc) (rand-int 11)])) (defc data1 (data!)) (defc data2 (data!)) (defc pwidth 400) (defc paused? false) (defc= series1 (take-last 10 data1)) (defc= series2 (take-last 10 data2)) (defc= chart1 (let [min-x (max (ffirst series1) (ffirst series2)) max-x (min (first (last series1)) (first (last series2)))] (c/config :width pwidth :height 200 :min-x min-x :max-x max-x :min-y 0 :max-y 10))) (with-init! (with-interval 1000 (when-not (clojure.core/deref paused?) (swap! data1 add!) (swap! data2 add!)))) (html :lang "en" (head (meta :charset "UTF-8") (title "Hoplon • Chart Demo")) (body (div :css {:width "400px", :margin "0 auto", :text-align "center", :padding "20px", :font-family "sans-serif"} (h2 "Hoplon • Chart Demo") (hr) (p "Click the plot area to pause/play. " "Hover over a point to see its coordinates. " "Slide the slider to adjust plot width.") (c/container :chart chart1 :css (cell= {:border "1px solid black", :margin-left (str "-" (- (/ (:width chart1) 2) 200) "px")}) :on-click (fn* [] (swap! paused? not)) (c/polygon :chart chart1 :data series1 :css {:fill c1, :stroke "none", :fill-opacity 0.5}) (c/polygon :chart chart1 :data series2 :css {:fill c2, :stroke "none", :fill-opacity 0.5}) (c/polyline :chart chart1 :data series1 :css {:fill "none", :stroke c1, :stroke-width 2}) (c/polyline :chart chart1 :data series2 :css {:fill "none", :stroke c2, :stroke-width 2}) (c/points-circle :chart chart1 :data series1 :r 3 :css {:stroke c1, :fill c1}) (c/points-rect :chart chart1 :data series2 :width 6 :height 6 :css {:stroke c2, :fill c2})) (br) (input :id "w" :style "width:400px" :type "range" :min 400 :max 800 :step 1 :value 400 :on-change (fn* [] (reset! pwidth (val-id "w")))) (p (a :href src-url "Source Code"))))) (tailrecursion.hoplon/init))