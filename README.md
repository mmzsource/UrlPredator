URL Predator
============

URL predator hunts down the url(s) you provide and consumes them with flesh and bones. It even hunts down the children of the provided url(s) recursively. Every trespassing URL will be torn to pieces. DO NOT APPROACH [THIS CUTE LITTLE FELLA](http://www.rgcblog.com/external/predator-bw.jpg) UNARMED!!!!1!!

## Context

### Situation

You don't like serving dead links from your applications. You don't like writing automated tests for each and every served URL, and won't even think about testing them manually.

#### Example

Let's asume you're serving a product catalog on http://domainname/api/v1/products. Calling GET on this URL results in this json message:

```json
[
  {
   "id": "1",
   "description": "Product1",
   "url": "http://domainname/api/v1/products/1"
  }, 
  {
   "id": "2",
   "description": "Product2",
   "url": "http://domainname/api/v1/products/2"
  }, 
  {
    etc..
  }
]
```

Calling GET on "http://domainname/api/v1/products/1" will result in something like this:

```json
{
  "id": "1",
  "description": "Product1",
  "details": "What-eva",
  "image": "http://domainname/api/v1/products/images/product1.jpg"
}
```

... and of course you would also like to know if GETting http://domainname/api/v1/products/images/product1.jpg will serve your customers a nice picture and not a 404, a 500 or one of those other dreaded statusses.

### Solution

Let the UrlPredator hunt a (collection of) URL(s) you provide and let the journal tell you how alive the links served by your application really are.

### Nasty side effect

All hunted URLs will die in the process. You just can't have it all, can you?!

## Usage

### Prerequisites

1. Have a JRE installed on your machine (UrlPredator is tested with java 7)
2. Download the UrlPredator.jar to your machine

### Unleash the predator

Open up a console:

```bash
cd to/the/directory/containing/the/UrlPredator.jar
java -jar UrlPredator.jar -host <your_host> -port <your_port_number> -path <your_first_path> <your_second_path> <your_nth_path>
```

Wait for the Journal to report the slaughter in the console. Of course, it's also possible to redirect the output to a file:

```bash
!! > Slaughter.txt
```

### Example

```bash
cd /Users/admin/downloads
java -jar UrlPredator.jar -host localhost -port 8080 -path /api/v1/products

### Console output:

#### Summary #### 

Hunted down 71 urls: 
 71 url(s) were eaten alive 
 0 url(s) were dead on arrival 

#### Alive URLs: #### 

http://localhost:8080/api/v1/products/
http://localhost:8080/api/v1/products/images/9c81e0b0.jpg
http://localhost:8080/api/v1/products/images/d772f07e.jpg
http://localhost:8080/api/v1/products/images/a798242e.jpg
http://localhost:8080/api/v1/products/images/ab03e812.jpg
http://localhost:8080/api/v1/products/images/aa80b9a6.jpg
http://localhost:8080/api/v1/products/images/7378c85d.jpg
http://localhost:8080/api/v1/products/images/bb1ea5b8.jpg
http://localhost:8080/api/v1/products/images/5663540c.jpg
http://localhost:8080/api/v1/products/images/252bfb8e.jpg
http://localhost:8080/api/v1/products/images/d5169830.jpg
http://localhost:8080/api/v1/products/47
http://localhost:8080/api/v1/products/21
http://localhost:8080/api/v1/products/48
http://localhost:8080/api/v1/products/22
http://localhost:8080/api/v1/products/45
http://localhost:8080/api/v1/products/images/42693740.jpg
http://localhost:8080/api/v1/products/images/d768bf17.jpg
http://localhost:8080/api/v1/products/46
http://localhost:8080/api/v1/products/20
http://localhost:8080/api/v1/products/49
http://localhost:8080/api/v1/products/images/63c4babb.jpg
http://localhost:8080/api/v1/products/images/440506ac.jpg
http://localhost:8080/api/v1/products/19
http://localhost:8080/api/v1/products/50
http://localhost:8080/api/v1/products/18
http://localhost:8080/api/v1/products/images/e71e377e.jpg
http://localhost:8080/api/v1/products/images/dbb8622e.jpg
http://localhost:8080/api/v1/products/17
http://localhost:8080/api/v1/products/16
http://localhost:8080/api/v1/products/15
http://localhost:8080/api/v1/products/14
http://localhost:8080/api/v1/products/13
http://localhost:8080/api/v1/products/12
http://localhost:8080/api/v1/products/images/3fe5fe11.jpg
http://localhost:8080/api/v1/products/images/f24a765d.jpg
http://localhost:8080/api/v1/products/images/2c08ac13.jpg
http://localhost:8080/api/v1/products/images/ccc93530.jpg
http://localhost:8080/api/v1/products/images/86e3c189.jpg
http://localhost:8080/api/v1/products/images/f67107e5.jpg
http://localhost:8080/api/v1/products/images/31666b33.jpg
http://localhost:8080/api/v1/products/30
http://localhost:8080/api/v1/products/31
http://localhost:8080/api/v1/products/32
http://localhost:8080/api/v1/products/33
http://localhost:8080/api/v1/products/images/2c5360ca.jpg
http://localhost:8080/api/v1/products/images/7e6b42cc.jpg
http://localhost:8080/api/v1/products/images/71fda48a.jpg
http://localhost:8080/api/v1/products/28
http://localhost:8080/api/v1/products/images/4f2c19cc.jpg
http://localhost:8080/api/v1/products/27
http://localhost:8080/api/v1/products/images/f7cd21ee.jpg
http://localhost:8080/api/v1/products/29
http://localhost:8080/api/v1/products/images/55954317.jpg
http://localhost:8080/api/v1/products/images/eeb816d7.jpg
http://localhost:8080/api/v1/products/images/acf44358.jpg
http://localhost:8080/api/v1/products/40
http://localhost:8080/api/v1/products/images/78dbb72a.jpg
http://localhost:8080/api/v1/products/43
http://localhost:8080/api/v1/products/44
http://localhost:8080/api/v1/products/41
http://localhost:8080/api/v1/products/42
http://localhost:8080/api/v1/products/37
http://localhost:8080/api/v1/products/36
http://localhost:8080/api/v1/products/35
http://localhost:8080/api/v1/products/34
http://localhost:8080/api/v1/products/images/40ad972b.jpg
http://localhost:8080/api/v1/products/39
http://localhost:8080/api/v1/products/images/6cfbd0fe.jpg
http://localhost:8080/api/v1/products/38
http://localhost:8080/api/v1/products/images/deb911bf.jpg

#### Dead URLs: #### 

``` 

## Known Limitations

- UrlPredator only GETs URLs. No POSTcards or negotiation OPTIONS with this pumpkin.
- UrlPredator only hunts absolute urls (like 'http://localhost:8080/api/v1/products/12' ). It currently doesn't work with relative URLs (like '/products/12').
- UrlPredator currently only hunts **http** URLs. (No https, etc.)
- UrlPredator only hunts URLs which comply with the host pattern. One time it hunted down other URL patterns as well, but this made it sick to its stomach (as well as slaughtered half the internet).

## Notes for developers

UrlPredator is a java console program, build with maven. It should be trivial to import the project in your favourite IDE as a maven project. Run the maven clean and package tasks to generate the jar. 

### Application

Contains the main method where all parts are wired. Fires up the Predator.

### Config

Very minimal class to parse the command line arguments.

### UrlPredator

Calls URLs recursively (see constructor). Interprets GET Responses as Strings and splits the Response into parts, based on characters which are not allowed in URLs. Tries to create new java.net.URLs from the parts. If a part cannot be converted to a URL, the part clearly isn't a URL. (You could expect a brute force attack from a predator).

### Journal

Records and reports the slaughter. 

### TODOs

See TODO.md document in the root of this project. Feel free to submit pull requests.

## License

You may use the UrlPredator and its source code for any purpose at all at your own risk. It is not copyrighted. Happy hunting!

