# `sanity-edn`: a light Clojure wrapper for Sanity

## PRE-ALPHA STATUS

`sanity-edn` is experimental software.
The `sanity-edn` API is expected to change.

## Dependency coordinates

Edit your `deps.edn` manually:

    {:deps {io.github.teodorlu/sanity-edn {:git/url "https://github.com/teodorlu/sanity-edn"
                                           :git/sha "430082175970d2d94fa82e80fd674c63d7beeb44"}}}

Or install with [neil](https://github.com/babashka/neil)

    neil dep add io.github.teodorlu/sanity-edn

## Usage

``` clojure
(ns example
  (:require [teodorlu.sanity-edn :as sanity]))

(defn lookup-your-sanity-secret [] ,,,)

(def client (sanity/client {:project-id "abcd1234"
                            :api-version "v2023-01-10"
                            :dataset "production"
                            :secret (lookup-your-sanity-secret)}))

(sanity/query client
              {:query "raw groq"
               :params {:startDate "2021-01-01T00:00:01Z"
                        :endDate "2022-01-01T00:00:01Z"}})
;; returns EDN
```

## Open questions

1. Should I use cheshire for JSON?
2. Should I use http-kit for HTTP requests?
3. Should I do anything about GROQ other than allowing a raw string?
    - next.jdbc just uses raw strings -- and leaves fancy DSLs to other libraries
    - I could provide a narrow `teodorlu.sanity-edn.grow-builder` namespace to help with common stuff

## Sanity configuration

You may obtain a sanity secret from `https://www.sanity.io/organizations/YOUR_ORGANIZATION/project/YOUR_PROJECT`.

## Sanity queries: GROQ

Sanity has its own query language: GROQ.

GROQ query documentation: https://www.sanity.io/docs/how-queries-work
