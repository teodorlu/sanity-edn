# `sanity-edn`: a light Clojure wrapper for Sanity

## PRE-ALPHA STATUS

`sanity-edn` is experimental software.
As of 2023-01-10, consider reading the `sanity_edn.clj` rather than introducing a dependency.

## Dependency coordinates

Edit your `deps.edn` manually:

    {:deps {io.github.teodorlu/sanity-edn {:mvn/version "TODO"}}}

Or install with [neil](https://github.com/babashka/neil)

    neil dep add io.github.teodorlu/sanity-edn

## Usage

``` clojure
(ns yourapp
  (:require [teodorlu.sanity-edn :as sanity]))

(def client (sanity/client {:project-id "abcd1234"
                            :api-version "v2023-01-10"
                            :dataset "production"}))

(sanity/query client
              {:query "raw groq"
               :parameters {:startDate "2021-01-01T00:00:01Z"
                            :endDate "2022-01-01T00:00:01Z"}})
;; returns edn
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
