(ns tailrecursion.hoplon.app-pages._index_DOT_html (:require [selectize :as s] [tailrecursion.hoplon.reload :refer [reload-all]] [tailrecursion.javelin :refer [alts! cell-map propagate! next-rank deref* lift cell input? cell-doseq* bf-seq destroy-cell! last-rank set-cell! set-formula! cell?]] [tailrecursion.hoplon :refer [script do! track article noscript command h4 h3 mark basefont h5 span input h2 th label h6 pre nav vector?* address sup h1 table font option datalist u safe-nth on! footer select q samp source summary li p td noframes node? iframe rel tr s *initfns* add-attributes! colgroup relx html dfn optgroup tbody text-val! ul hgroup sub strong data progress loop-tpl* acronym append-child replace-children! img details fieldset html-head em html-time rt when-dom video keygen div val-id dt ol link init form is-ie8 check-val! menu timeout del a parse-args area legend hr dir header param meter tfoot blockquote eventsource b dl figcaption caption route-cell style rel-event abbr ruby applet html-meta bdi embed rp figure on-append! canvas section object strike title button output audio initialized? add-children! dd bdo cite code kbd big seq?* frame rely col tt i ins thead unsplice isindex frameset center spliced base $text by-id $comment br textarea wbr html-map small add-initfn! html-body aside html-var]]) (:require-macros [tailrecursion.javelin :refer [cell-doseq cell= defc defc= mx with-let prop-cell mx2 cell-let set-cell!= macroexpand-all]] [tailrecursion.hoplon :refer [with-init! body text defelem loop-tpl head with-timeout with-interval def-values flatten-expr]]))

(clojure.core/defn ^:export hoploninit [] (reload-all) (defn rotten-tomatoes [query callback] (.ajax js/jQuery (clj->js {:url "http://api.rottentomatoes.com/api/public/v1.0/movies.json", :type "GET", :dataType "jsonp", :data {:q query, :page_limit 10, :apikey "jv2szsy84jnsbj8cdtxt866m"}, :error (fn* [] (callback)), :success (fn* [p1__5373#] (callback (aget p1__5373# "movies")))}))) (defn movie-option [movie] (let [{:strs [title synopsis abridged_cast]} movie] (div (img :src (get-in movie ["posters" "thumbnail"])) (span :class "title" (span :class "name" title)) (span :class "description" (if (empty? synopsis) "No synopsis available at this time." synopsis)) (span :class "actors" (if (seq abridged_cast) (->> abridged_cast (map (comp span (fn* [p1__5374#] (get p1__5374# "name")))) (interpose ", ") (cons "Starring ")) "Actors unavailable."))))) (html (head (title "Hoplon Selectize") (link :rel "stylesheet" :type "text/css" :href "selectize.css") (link :rel "stylesheet" :type "text/css" :href "selectize_movies.css")) (body :style "font-family: sans-serif; width: 40%; margin: auto;" (h3 "Rotten Tomatoes Movie Title Search") (let [movies (cell {}) movie-id (cell nil) movie (cell= (get movies movie-id)) poster (cell= (get-in movie ["posters" "detailed"])) title (cell= (get movie "title")) year (cell= (get movie "year")) starring (cell= (map (fn* [p1__5375#] (get p1__5375# "name")) (get movie "abridged_cast")))] (div (s/selectize :cell movie-id :class "movies" :valueField "id" :labelField "title" :searchField "title" :create false :render {:option (fn* [p1__5376#] (do (swap! movies assoc (get p1__5376# "id") p1__5376#) (movie-option p1__5376#)))} :load rotten-tomatoes) (div :toggle movie (img :style "float:right;" :src poster) (h2 (text "~{title} (~{year})")) (h2 "Starring") (ul (loop-tpl :bindings [star starring] (li (text "~{star}"))))) (div :style "text-align:center;" (a :href "https://github.com/tailrecursion/hoplon-demos/blob/master/selectize/src/index.cljs.hl" "Hoplon Source Code")))))) (tailrecursion.hoplon/init))