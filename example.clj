(ns yourapp
  (:require [teodorlu.sanity-edn :as sanity]))

(def client (sanity/client {:project-id "abcd1234"
                            :api-version "v2023-01-10"
                            :dataset "production"
                            :secret (lookup-your-sanity-secret)}))

(sanity/query client
              {:query "raw groq"
               :parameters {:startDate "2021-01-01T00:00:01Z"
                            :endDate "2022-01-01T00:00:01Z"}})
;; returns edn
