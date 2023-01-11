(ns teodorlu.sanity-edn
  (:require
   [org.httpkit.client :as http]
   [cheshire.core :as json]))

(defn client [opts]
  (doseq [k [:project-id :api-version :dataset :secret]]
    (assert (contains? opts k) (str "Option required: " k)))
  (assoc opts ::client-valid? true))

(defn query-lowlevel
  "Run a GROQ query against a Sanity database, return raw HTTP response"
  [client {:keys [query params]}]
  (assert (::client-valid? client))
  (assert query)
  (let [{:keys [project-id api-version dataset secret]} client
        url (str "https://" project-id ".api.sanity.io/" api-version "/data/query/" dataset)
        headers {"Authorization" (str "Bearer " secret)
                 "Content-Type" "application/json"}
        ;; _ (println (json/generate-string {:query query :params params} {:pretty true}))
        response (-> (http/request {:method :post
                                    :url url
                                    :headers headers
                                    :body (json/generate-string {:query query :params params})})
                     deref)]
    response))

(defn query
  "Run a GROQ query against a Sanity database, return parsed EDN"
  [client {:keys [query params]}]
  (assert (::client-valid? client))
  (assert query)
  (let [response (query-lowlevel client {:query query :params params})]
    (assert (= (:status response) 200))
    (json/parse-string (:body (query-lowlevel client {:query query :params params}))
                       keyword)))
