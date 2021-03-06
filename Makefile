# all tests, builds and runs both services
.PHONY: all
all: test build run

# clean-tests both projects
.PHONY: test
test: 
	@echo "Testing product-review..."
#	@mvn test -f ./product-review/pom.xml
	@echo "Testing product-service..."
	@mvn test -f ./product-service/pom.xml

# builds docker images for both projects
.PHONY: build
build:
	@echo "Building..."
	@docker-compose build 

# runs both applications in detached mode
.PHONY: run
run:
	@echo "Running..."
	@docker-compose up -d
