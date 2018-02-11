# *URL SHORTENER*

**URL SHORTENER** is a link shortener like bit.ly. This is a web service with an HTTP API. It will have two endpoints: one for creating short links and one for resolving a short link. 

**NOTE**: This is more a system design problem and therefore implemented by mocking the HTTP API for now.

Below is the specification for the two endpoints:

* [x] Resolve short link
  * [x] Request
    * [x] GET /:linkId
  * [x]	Response
    * [X] 302 Found
      * [x] Location -> response header is the target URL
    * [x] 404 Not Found
* [x] Create short link
  * [x] Request
    * [x] POST /
      * [x] url -> the URL to shorten
      * [x] id  -> (optional) a friendly link ID instead of a randomly generated one
  * [x]	Response
    * [X] 201 Created -> if creation is successful
      * [x] Location -> response header is the ne short URL
      * [x] The ID of the link is either the ID specified or a randomly generated ID consisting of 6 alphanumeric characters.
    * [x] 409 Conflict -> if id specified already exists
    * [x] 429 Too Many Requests -> if we have hit our global rate limit (see bonus)

**Bonus** features:

* [ ] Links must persist upon app restart (using a database is not strictly a requirement)
* [ ] Requests to create short links are limited to 100/hour

## Video Walkthrough 

Here's a walkthrough of the implementation:

![demo](VideoWalkthrough/walkthrough.gif)

## License

    Copyright [2018] kprav

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
