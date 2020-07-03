# StockAvailabilityChecker
Silly Project to check availability of an item at certain retailers - using this readme as a braindump 

Currently "Supports" Harrods and Fenwicks -

# Running

Uses Selenium to webscrape which is then processed with a Java backend.

Main function can be found in 'Stock Availability Checker', some basic fuctionality has been commented out, but the jist is that it creates two schedule checkeres one for Fenwicks and one for Harrods.

(The schedulers are defined in the sub-package under utils), you will need to enter your email credentials in both of the schedulers (may change to using environments variables in the future)

Currently it is setup using googles SMTP server, so only Gmail as the "sender" is supported.


