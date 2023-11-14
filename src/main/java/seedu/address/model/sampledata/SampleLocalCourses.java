package seedu.address.model.sampledata;

import java.util.Arrays;

import seedu.address.model.localcourse.LocalCode;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.localcourse.LocalDescription;
import seedu.address.model.localcourse.LocalName;
import seedu.address.model.localcourse.LocalUnit;

/**
 * A utility class that provides sample local courses for seeding the application.
 */
public class SampleLocalCourses {
    /**
     * The `SampleCourseCode` enum represents the sample course codes.
     */
    public enum SampleLocalCourse {
        BT1101("BT1101", "Introduction to Business Analytics",
            "This course provides students with an introduction to the fundamental concepts and tools needed to "
                + "understand the emerging role of business analytics and data science applications in business and "
            + "non-profit organisations. Students will learn how to apply basic business analytics and data "
                + "science/analytics tools (such as R) to large real-life datasets in different contexts, and how to "
            + "effectively use and interpret analytic models and results for making better and more well-informed "
            + "business decisions. This course will provide both the organisational and technical aspects of business"
            + " analytics and serves to provide students with a broad overview of how and why business analytics can "
            + "be implemented in organisations, the various approaches and techniques that could be adopted for "
            + "different organisational objectives and issues."),
        BT2101("BT2101", "Econometrics Modeling for Business Analytics",
            "This course provides the foundations to econometrics and the use of software tools to perform "
            + "econometrics data analysis. The course cover analysis models, including regression, time series "
            + "regression, dynamic causal effects among others. Examples will also be drawn from various industry "
            + "domains and applications."),
        BT2102("BT2102", "Data Management and Visualisation",
            "This course aims to provide students with practical knowledge and understanding of basic issues and "
            + "techniques in data management and warehousing with relational database management systems, as well as "
            + "data visualisation principles, techniques and tools. The course covers data management concepts, "
            + "conceptual and logical design, database management, data warehousing concepts, data warehousing "
            + "design, relational database management systems and tools, data visualisation and dashboard design "
            + "concepts, visual perception and design principles, visualisation techniques and tools for temporal and"
            + " spatial data, proportions and relationships, multivariate and complex data, etc."),
        BT3017("BT3017", "Feature Engineering for Machine Learning",
            "This course covers topics that are important for preparation of data and features for machine learning. "
            + "Real life raw data and features extracted from these data often have inherent problems that need to be"
            + " fixed before feeding to machine learning systems. Such problems include missing data; redundant data;"
            + " data that are not in numerical form; data that are of different scales and statistical distributions;"
            + " outliers; unstructured data such as audio, picture, video, and text; huge dimensionality of features "
            + "that need higher level of abstraction, etc. This course teaches students how to handle such problems "
            + "that occur in the real world."),
        BT3102("BT3102", "Computational Methods for Business Analytics",
            "Computers are becoming readily accessible, and its use in business analytics is growing more prevalent. "
            + "This course will introduce students to computational methods, algorithms and techniques used in "
            + "business fields such as finance, marketing and economics to address complex analytics problems. The "
            + "course will cover topics and algorithms in areas such as optimization methods, numerical analysis, "
                + "simulations, monte-carlo methods, graph and flow methods, and computational complexity issues to "
            + "address business analytics related problems. Students will get the opportunity to learn about these "
            + "computational methods and issues through hands-on practice assignments, case analysis discussions, and"
            + " course projects."),
        BT3103("BT3103", "Application Systems Development for Business Analytics",
            "This course aims to train students to be conversant in the technologies, approaches, principles and "
            + "issues in designing IT applications systems for business analytics. Major topics include: rapid web "
            + "frameworks, scripting languages, database design, web and mobile interfaces, tracking and analysis of "
            + "customers, payment services / verification, implementing security, designing and deploying web and "
            + "mobile services, and operational considerations and technical tradeoffs."),
        BT3104("BT3104", "Optimization Methods for Business Analytics",
            "Business analytics revolves around searching for valuable insights hidden in the data so that better "
            + "decisions can be taken. Developments in machine learning techniques enables the application of "
            + "powerful algorithms for business data analytics. At the core of these techniques are optimization "
            + "methods which find the best solution among all possible alternatives. This course will cover "
            + "optimization algorithms that are widely applied in machine learning. The following topics will be "
            + "covered (1) linear programming, (2) unconstrained nonlinear programming, and (3) constrained nonlinear"
            + " programming, their applications in machine learning and business analytics will be emphasized."),
        BT4012("BT4012", "Fraud Analytics",
            "This course provides you with the foundational application of analytics in the audit and investigation "
            + "processes. Students will have an opportunity to gain technological and managerial overview of "
            + "analytical techniques, link analytics, continuous monitoring of business activities, and analytics "
            + "reporting. The crimes to be covered in this course include fraud, money laundering, terror financing, "
            + "collusion, market manipulation, cyber intrusion, and control lapses."),
        BT4013("BT4013", "Analytics for Capital Market Trading and Investment",
            "This course offers a broad coverage of quantitative trading and financial portfolio optimization, which "
            + "consists of trading strategies based on quantitative analysis. It will also aim to identify trading "
            + "opportunities, practices, optimal execution and placements of trading on current technological "
            + "platforms. Regulations and risk management of quantitative trading will also be emphasized."),
        BT4014("BT4014", "Analytics Driven Design of Adaptive Systems",
            "To design technology that impacts people – in education, health, business – this course introduces "
            + "methods for creating systems that use data intelligently to improve themselves. This requires "
            + "combining human intelligence (using methods like crowdsourcing, collaborative design) with artificial "
            + "intelligence (discovering which technology designs help which people) through designing randomized A/B"
            + " experiments that are collaborative, dynamic, and personalized."),
        BT4015("BT4015", "Geospatial Analytics",
            "This course will offer an in-depth coverage of geospatial analysis, starting with the transferring of "
            + "knowledge in the gathering, display, and manipulation of imagery, GPS, satellite photography and "
            + "historical data. Geospatial storage tools such as PostGIS that have been created to provide a more "
            + "scalable backend will be introduced. Other geospatial tools, such as ArcGIS, will be covered. "
            + "Exploratory Spatial and Spatio-temporal Data Analysis (ESDA, ESTDA) and spatial statistics, including "
            + "spatial autocorrelation and spatial regression are some of the geospatial data analytics methods "
            + "covered in detail on the variety of business contexts, operations and applications."),
        BT4016("BT4016", "Risk Analytics for Financial Services",
            "This course exposes students to fundamentals of risk analytics in financial service sector. Students "
            + "will be taught on the fundamentals of financial services and financial risks. They also learn about "
            + "interest risk analytics and credit risk analytics."),
        BT4211("BT4211", "Data-Driven Marketing",
            "In today’s environment, marketing or business analysts require tools and techniques to both quantify the"
            + " strategic value of marketing initiatives, and to maximize marketing campaign performance. This course"
            + " aims to teach students concepts, methods and tools to demonstrate the return on investment (ROI) of "
            + "marketing activities and to leverage on data and marketing analytics to make better and more informed "
            + "marketing decisions. Course topics covered include marketing performance management, marketing "
            + "metrics, data management, market response and diffusion models, market and customer segmentation "
            + "models, analytic marketing and value driven segmentation, digital media marketing analytics, etc. "
            + "Students will have access to"),
        BT4212("BT4212", "Search Engine Optimization and Analytics",
            "This course teaches the concepts, techniques and methods to analyse and improve the visibility of a "
            + "website or a web page in search engines via the “natural” or un-paid (“organic” or “algorithmic”) "
            + "search results. Students will be taught concepts and knowledge in terms of how search engines work, "
            + "what people search for, what are the actual search terms or keywords typed into search engines, which "
            + "search engines are preferred by their targeted audience, and how to optimize a website in terms of "
            + "editing its content, structure and links, and associated coding to both increase its relevance to "
            + "specific keywords and to remove barriers to the indexing activities of search engines. Importantly, "
            + "the course will emphasize the relationship of search engine optimization to digital marketing in terms"
            + " of building high quality web pages to engage and persuade, setting up analytics programs to enable "
            + "sites to measure results, and improving a site's conversion rate."),
        BT4221("BT4221", "Big Data Techniques and Technologies",
            "This course teaches students concepts, techniques and technologies in handling and analyzing big data "
            + "sets, i.e., data sets whose size is beyond the ability of commonly used software tools to capture, "
            + "manage, and process the data within a tolerable elapsed time. Common sources and domains of big data "
            + "include ubiquitous information-sensing mobile devices, web and software logs, financial trading "
            + "transactions, large-scale e-commerce, RFID and wireless sensor networks, etc. Conceptual big data "
            + "topics covered include big data instructure, analytics scalability and processes, etc. Technical "
            + "frameworks for big data handling and analytics taught include Hadoop, HBase, Cassandra, MapReduce, "
            + "Dynamo, R, in-database analytics,"),
        BT4222("BT4222", "Mining Web Data for Business Insights",
            "The World Wide Web overwhelms us with immense amounts of widely distributed, interconnected, rich, and "
            + "dynamic hypertext information. It has profoundly influenced many aspects of our lives, changing the "
            + "ways individuals communicate and the manners businesses are conducted. This course aims to teach "
            + "students various concepts, methods and tools in mining Web data in the form of unstructured Web "
            + "hyperlinks, page contents, and usage logs to uncover deep business insights and knowledge for business"
            + " implications that are embedded in the billions of Web pages and servers. Topics covered include "
            + "various text mining methodologies, case applications and tutorials on Web data"),
        BT4240("BT4240", "Machine Learning for Predictive Data Analytics",
            "This course provides a comprehensive coverage of methods and tools for predictive data analytics. "
            + "Various techniques from data mining, statistics and artificial intelligence will be discussed. The "
            + "emphasis will be on more recent developments in machine learning methods such as neural networks and "
            + "support vector machines that have been shown to be very effective in discovering reliable patterns "
            + "from past data and making accurate predictions of future outcomes. Applications of predictive "
            + "analytics in business will also be discussed."),
        BT4301("BT4301", "Business Analytics Solutions Development and Deployment",
            "This course introduces students to essential concepts and knowledge on developing business analytics "
            + "(BA) solutions and deploying them in production environments. Students will learn frameworks, tools "
            + "and techniques that enable them to participate in and manage the processes of BA project life cycle "
            + "(such as designing and developing models, preparing for production, deploying to production, "
            + "monitoring and feedback loop). They will also learn about issues on data/model governance."),
        CG2271("CG2271", "Real-Time Operating Systems",
            "Real-time systems must respond quickly to inputs from the environment in order to work effectively and "
            + "safely, and realtime operating systems (RTOS) are a critical part of such systems. In this course the "
            + "student is exposed to basic RTOS concepts like tasks, scheduling algorithms, RTOS customisation and "
            + "concurrent real-time programming. By the end of this course a student will not only understand how an "
            + "RTOS is built, but will also gain practical hands-on experience in customising RTOSs and in writing "
            + "real-time programs."),
        CS1010("CS1010", "Programming Methodology",
            "This course introduces the fundamental concepts of problem solving by computing and programming using an"
            + " imperative programming language. It is the first and foremost introductory course to computing. "
            + "Topics covered include computational thinking and computational problem solving, designing and "
            + "specifying an algorithm, basic problem formulation and problem solving approaches, program "
            + "development, coding, testing and debugging, fundamental programming constructs (variables, types, "
            + "expressions, assignments, functions, control structures, etc.), fundamental data structures (arrays, "
            + "strings, composite data types), basic sorting, and recursion."),
        CS1010E("CS1010E", "Programming Methodology",
            "This course introduces the fundamental concepts of problem solving by computing and programming using an"
            + " imperative programming language. It is the first and foremost introductory course to computing. "
            + "Topics covered include computational thinking and computational problem solving, designing and "
            + "specifying an algorithm, basic problem formulation and problem solving approaches, program "
            + "development, coding, testing and debugging, fundamental programming constructs (variables, types, "
            + "expressions, assignments, functions, control structures, etc.), fundamental data structures (arrays, "
            + "strings, composite data types), basic sorting, and recursion."),
        CS1010J("CS1010J", "Programming Methodology",
            "This course introduces the fundamental concepts of problem solving by computing and programming using an"
            + " imperative programming language. It is the first and foremost introductory course to computing. "
            + "Topics covered include computational thinking and computational problem solving, designing and "
            + "specifying an algorithm, basic problem formulation and problem solving approaches, program "
            + "development, coding, testing and debugging, fundamental programming constructs (variables, types, "
            + "expressions, assignments, functions, control structures, etc.), fundamental data structures (arrays, "
            + "strings, composite data types), basic sorting, and recursion."),
        CS1010S("CS1010S", "Programming Methodology",
            "This course introduces the fundamental concepts of problem solving by computing and programming using an"
            + " imperative programming language. It is the first and foremost introductory course to computing and is"
            + " equivalent to CS1010 and CS1010E Programming Methodology. Topics covered include problem solving by "
            + "computing, writing pseudo-codes, basic problem formulation and problem solving, program development, "
            + "coding, testing and debugging, fundamental programming constructs (variables, types, expressions, "
            + "assignments, functions, control structures, etc.), fundamental data structures: arrays, strings and "
            + "structures, simple file processing, and basic recursion. This course is appropriate for FoS students."),
        CS1231("CS1231", "Discrete Structures",
            "This course introduces mathematical tools required in the study of computer science. Topics include: (1)"
            + " Logic and proof techniques: propositions, conditionals, quantifications. (2) Relations and Functions:"
            + " Equivalence relations and partitions. Partially ordered sets. Well-Ordering Principle. Function "
            + "equality. Boolean/identity/inverse functions. Bijection. (3) Mathematical formulation of data models "
            + "(linear model, trees, graphs). (4) Counting and Combinatoric: Pigeonhole Principle. "
            + "Inclusion-Exclusion Principle. Number of relations on a set, number of injections from one finite set "
            + "to another, Diagonalisation proof: An infinite countable set has an uncountable power set; Algorithmic"
            + " proof: An infinite set has a countably infinite subset. Subsets of countable sets are countable."),
        CS2010("CS2010", "Data Structures and Algorithms II",
               "This course is a follow up to CS2040S."),
        CS2030("CS2030", "Programming Methodology II",
            "This course is a follow up to CS1010. It explores two modern programming paradigms, object-oriented "
            + "programming and functional programming. Through a series of integrated assignments, students will "
            + "learn to develop medium-scale software programs in the order of thousands of lines of code and tens of"
            + " classes using object oriented design principles and advanced programming constructs available in the "
            + "two paradigms. Topics include objects and classes, composition, association, inheritance, interface, "
            + "polymorphism, abstract classes, dynamic binding, lambda expression, effect-free programming, first "
            + "class functions, closures, continuations, monad, etc."),
        CS2040("CS2040", "Data Structures and Algorithms",
            "This course introduces students to the design and implementation of fundamental data structures and "
            + "algorithms. The course covers basic data structures (linked lists, stacks, queues, hash tables, binary"
            + " heaps, trees, and graphs), searching and sorting algorithms, and basic analysis of algorithms."),
        CS2040C("CS2040C", "Data Structures and Algorithms",
            "This course introduces students to the design and implementation of fundamental data structures and "
            + "algorithms. The course covers basic data structures (linked lists, stacks, queues, hash tables, binary"
            + " heaps, trees, and graphs), searching and sorting algorithms, basic analysis of algorithms, and basic "
            + "object-oriented programming concepts."),
        CS2100("CS2100", "Computer Organisation",
            "The objective of this course is to familiarise students with the fundamentals of computing devices. "
            + "Through this course students will understand the basics of data representation, and how the various "
            + "parts of a computer work, separately and with each other. This allows students to understand the "
            + "issues in computing devices, and how these issues affect the implementation of solutions. Topics "
            + "covered include data representation systems, combinational and sequential circuit design techniques, "
            + "assembly language, processor execution cycles, pipelining, memory hierarchy and input/output systems."),
        CS2102("CS2102", "Database Systems",
            "The aim of this course is to introduce the fundamental concepts and techniques necessary for the "
            + "understanding and practice of design and implementation of database applications and of the management"
            + " of data with relational database management systems. The course covers practical and theoretical "
            + "aspects of design with entity-relationship model, theory of functional dependencies and normalisation "
            + "by decomposition in second, third and Boyce-Codd normal forms. The course covers practical and "
            + "theoretical aspects of programming with SQL data definition and manipulation sublanguages, relational "
            + "tuple calculus, relational domain calculus and relational algebra."),
        CS2103("CS2103", "Software Engineering",
            "This course introduces the necessary conceptual and analytical tools for systematic and rigorous "
            + "development of software systems. It covers four main areas of software development, namely "
            + "object-oriented system analysis, object-oriented system modelling and design, implementation, and "
            + "testing, with emphasis on system modelling and design and implementation of software courses that work"
            + " cooperatively to fulfill the requirements of the system. Tools and techniques for software "
            + "development, such as Unified Modelling Language (UML), program specification, and testing methods, "
            + "will be taught. Major software engineering issues such as modularisation criteria, program "
            + "correctness, and software quality will also be covered."),
        CS2104("CS2104", "Programming Language Concepts",
            "This course introduces the concepts that serve as a basis for hundreds of programming languages. It aims"
            + " to provide the students with a basic understanding and appreciation of the various essential "
            + "programming-languages constructs, programming paradigms, evaluation criteria and language "
            + "implementation issues. The course covers concepts from imperative, object-oriented, functional, logic,"
            + " constraints, and concurrent programming. These concepts are illustrated by examples from varieties of"
            + " languages such as Pascal, C, Java, Smalltalk, Scheme, Haskell, Prolog. The course also introduces "
            + "various implementation issues, such as pseudo-code interpretation, static and dynamic semantics, "
            + "abstract machine, type inferencing, etc."),
        CS2105("CS2105", "Introduction to Computer Networks",
            "This course aims to provide a broad introduction to computer networks and network application "
            + "programming. It covers the main concepts, the fundamental principles, and the high-level workings of "
            + "important protocols in each of the Internet protocol layer. Topics include the Web and Web "
            + "applications, DNS services, socket programming, reliable protocols, transport and network layer "
            + "protocols, secure communication, LAN, and data communication. Practical assignments and handson "
            + "exercises expose students to network application programming and various networking tools and "
            + "utilities."),
        CS2106("CS2106", "Introduction to Operating Systems",
            "This course introduces the basic concepts in operating systems and links it with contemporary operating "
            + "systems (eg. Unix/Linux and Windows). It focuses on OS structuring and architecture, processes, memory"
            + " management, concurrency and file systems. Topics include kernel architecture, system calls, "
            + "interrupts, models of processes, process abstraction and services, scheduling, review of physical "
            + "memory and memory management hardware, kernel memory management, virtual memory and paging, caches, "
            + "working set, deadlock, mutual exclusion, synchronisation mechanisms, data and metadata in file "
            + "systems, directories and structure, file system abstraction and operations, OS protection mechanisms, "
            + "and user authentication."),
        CS2107("CS2107", "Introduction to Information Security",
            "This course serves as an introductory course on information security. It illustrates the fundamentals of"
            + " how systems fail due to malicious activities and how they can be protected. The course also places "
            + "emphasis on the practices of secure programming and implementation. Topics covered include "
            + "classical/historical ciphers, introduction to modern ciphers and cryptosystems, ethical, legal and "
            + "organisational aspects, classic examples of direct attacks on computer systems such as input "
            + "validation vulnerability, examples of other forms of attack such as social engineering/phishing "
            + "attacks, and the practice of secure programming."),
        CS2108("CS2108", "Introduction to Media Computing",
            "This course introduces students to (i) the fundamental principles, theory, algorithms, and data "
            + "structures behind digital representation, compression, synchronization, and processing of image, "
            + "audio, and video data types, and (ii) challenges and issues in developing media-rich applications, "
            + "such as media streaming and media retrieval. Students will be exposed to the workings of common media "
            + "file format and common manipulation techniques on media data. After taking the course, students should"
            + " be confident enough in developing media applications and make appropriate trade-off and design "
            + "decisions when dealing in media data in their software."),
        CS2109S("CS2109S", "Introduction to AI and Machine Learning",
            "This course introduces basic concepts in Artificial Intelligence (AI) and Machine Learning (ML). It "
            + "adopts the perspective that planning, games, and learning are related types of search problems, and "
            + "examines the underlying issues, challenges and techniques. Planning/games related topics include "
            + "tree/graph search, A* search, local search, and adversarial search (e.g., games). Learning related "
            + "topics include supervised and unsupervised learning, model validation, and neural networks."),
        CS2113("CS2113", "Software Engineering & Object-Oriented Programming",
            "This course introduces the necessary skills for systematic and rigorous development of software systems."
            + " It covers requirements, design, implementation, quality assurance, and project management aspects of "
            + "small-to-medium size multi-person software projects. The course uses the Object Oriented Programming "
            + "paradigm. Students of this course will receive hands-on practice of tools commonly used in the "
            + "industry, such as test automation tools, build automation tools, and code revisioning tools will be "
            + "covered."),
        CS2220("CS2220", "Introduction to Computational Biology",
            "This course aims to develop flexible and logical problem solving skills, understanding of main "
            + "bioinformatics problems, and appreciation of main techniques and approaches to bioinformatics. Through"
            + " case studies and hands-on exercises, the student will (i) master the basic tools and approaches for "
            + "analysis of DNA sequences, protein sequences, gene expression profiles, etc. (ii) understand important"
            + " problems and applications of computational biology, including identifying functional features in DNA "
            + "and protein sequences, predicting protein function, and deriving diagnostic models from gene "
            + "expression profiles, (iii) be confident to propose new solutions to both existing and emerging "
            + "problems in computational biology."),
        CS2309("CS2309", "CS Research Methodology", "Not Available"),
        CS3103("CS3103", "Computer Networks Practice",
            "This course aims to provide an opportunity for the students to learn commonly-used network protocols in "
            + "greater technical depth with their implementation details than a basic networking course. Students "
            + "will perform hands-on experiments in configuring and interconnecting LANs using networking "
            + "devices/technologies (e.g., routers, switches, SDN switches, and hubs), networking protocols (e.g., "
            + "DHCP, DNS, RIP, OSPF, ICMP, TCP, UDP, wireless LAN, VLAN protocols, SIP, SSL, IPSec-VPN) and "
            + "networking tools (e.g, tcpdump, netstat, ping, traceroute). Students will learn higher-layer network "
            + "protocols and develop network applications (client/server, P2P) via socket programming."),
        CS3210("CS3210", "Parallel Computing",
            "The aim of this course is to provide an introduction to the field of parallel computing with hands-on "
            + "parallel programming experience on real parallel machines. The course is divided into four parts: "
            + "parallel computation models and parallelism, parallel architectures, parallel algorithm design and "
            + "programming, and new parallel computing models. Topics includes: theory of parallelism and models; "
            + "shared-memory architectures; distributed-memory architectures; data parallel architectures; "
            + "interconnection networks, topologies and basic of communication operations; principles of parallel "
            + "algorithm design; performance and scalability of parallel programs, overview of new parallel computing"
            + " models such as grid, cloud, GPGPU."),
        CS3211("CS3211", "Parallel and Concurrent Programming",
            "A concurrent system consists of a set of processes that executes simultaneously and that may collaborate"
            + " by communicating and synchronising with one another. Examples of concurrent systems are parallel "
            + "programs that describe sets of collaborating processes. This course introduces the design, development"
            + " and debugging of parallel programs. It will build on the concurrency concepts gained from the "
            + "Operating Systems course. It covers concepts and modelling tools for specifying and reasoning (about "
            + "the properties of) concurrent systems and parallel programs. It also covers principles of performance "
            + "analysis, asynchronous and asynchronous parallel programming, and engineering concurrent systems and "
            + "parallel programs."),
        CS3213("CS3213", "Foundations of Software Engineering",
            "This course will provide the students with foundational knowledge and understanding of different aspects"
            + " of software engineering including requirements, modelling, architecture, behavior, environment, "
            + "software analysis, validation and verification. It will serve as a second software engineering course "
            + "exposing the students to the mathematical foundations of software engineering while simultaneously "
            + "supplementing and elaborating the foundations in the form of a semester long project."),
        CS3216("CS3216", "Software Product Engineering for Digital Markets",
            "In this course, students will practice software product engineering by working in small teams to develop"
            + " well-tested, user-friendly, production-quality software for the real world. To support this goal, "
            + "students work closely with users to understand their problems, gather their requirements, and obtain "
            + "their feedback through a rapid, iterative, application design and development process. Students will "
            + "also be exposed to practical issues for digital markets such as growing the user base of their "
            + "application, deployment of the application on the Web or in the cloud system, and validating the UI "
            + "design and UX of the application."),
        CS3217("CS3217", "Software Engineering on Modern Application Platforms",
            "This course introduces students to the practice of software engineering on modern application platforms "
            + "such as mobile devices, the Web and cloud systems. Students will work in small project teams to "
            + "develop well-tested, production-quality software. This course focuses on building core software "
            + "engineering skills and competencies in programming modern application platforms. It also trains "
            + "students to work well in project teams. Students will be assessed on both their individual programming"
            + " competencies and their software enginnering skills in final team project."),
        CS3218("CS3218", "Multimodal Processing in Mobile Platforms",
            "Modern mobile platforms such as smart phones and tablets are equipped with an increasing number of "
            + "sensing modalities. In addition to traditional components such as keyboards and touch screens, they "
            + "are also equipped with cameras, microphones, inertial sensor, and GPS receivers. With these modalities"
            + " all packed into a single platform, it is important to empower application developers with basic "
            + "knowledge and practical skills in dealing with these modalities. This course introduces the students "
            + "to basic theories, concept and practical skills needed in input, processing and output of multimodal "
            + "data on mobile platforms."),
        CS3219("CS3219", "Software Engineering Principles and Patterns",
            "This course provides an in-depth, hands-on experience in key aspects of software engineering that "
            + "accompany the development of software. Based on proven principles and best practices, this course "
            + "focuses on software architectural design from the perspective of the software process. It covers "
            + "techniques for requirement elicitation and specification that provide sound base for architectural "
            + "design. The course covers design decision exploration as well as patterns that explicate principles "
            + "and best practices in replicable form."),
        CS3220("CS3220", "Computer Architecture",
            "The objective of this course is concerned with design techniques involving the use of parallelism to "
            + "improve the performance of computer systems. The course is divided into three parts. Part I considers "
            + "the fundamental methods to improve the performance of single processor systems. Topics include the "
            + "design principle of instruction set, memory hierarchy, pipeline design techniques, RISC and vector "
            + "computer. In Part II, multi-processor systems using shared memory are examined in detail, and Part "
            + "III, multi-processor systems that do not use shared memory are examined."),
        CS3221("CS3221", "Operating Systems Design and Pragmatics",
            "This course builds upon the conceptual foundation formed in CS2106 and extends it to the study of "
            + "real-life operating systems. The focus is to understand how actual operating systems work including "
            + "the pragmatics, system architecture and design and implementation. Details will be drawn from "
            + "contemporary operating systems such as Unix/Linux and Windows. Topics include kernel architecture and "
            + "interfaces, computer architecture issues, process APIs and implementation, threads, scheduling, "
            + "physical and kernel memory management, virtual memory, synchronisation and interprocess communication "
            + "mechanisms, multi-processor issues, device characteristics and management, file system implementation,"
            + " memory mapped files, special purpose file systems."),
        CS3223("CS3223", "Database Systems Implementation",
            "This system-oriented course provides an in-depth study of the concepts and implementation issues related"
            + " to database management systems. It first covers the physical implementation of the relational data "
            + "model, which includes storage management, access methods, query processing, and optimisation. Then it "
            + "covers issues and techniques dealing with multi-user application environments, namely, transactions, "
            + "concurrency control, and recovery. The third part covers advanced topics such as on-line analytical "
            + "processing, in-memory databases, and column stores."),
        CS3226("CS3226", "Web Programming and Applications",
            "This course introduces students to software development on the Web platforms. Students will be exposed "
            + "to important computer science concepts, including networking, databases, computer security, user "
            + "interface design, programming languages, and software engineering. These concepts will be tied "
            + "together through hands-on practice in building a Web-based application using the current Web "
            + "development technology. At the end of the course, students are expected to be able to design and "
            + "develop a Web application, to appreciate the underlying technology needed to build a Web application, "
            + "and to develop a fundamental understanding of related computer science concepts."),
        CS3230("CS3230", "Design and Analysis of Algorithms",
            "This course introduces different techniques of designing and analysing algorithms. Students will learn "
            + "about the framework for algorithm analysis, for example, lower bound arguments, average case analysis,"
            + " and the theory of NP-completeness. In addition, students are exposed to various algorithm design "
            + "paradigms. The course serves two purposes: to improve the students' ability to design algorithms in "
            + "different areas, and to prepare students for the study of more advanced algorithms. The course covers "
            + "lower and upper bounds, recurrences, basic algorithm paradigms (such as prune-and-search, dynamic "
            + "programming, branch-and-bound, graph traversal, and randomised approaches), amortized analysis, "
            + "NP-completeness, and some selected advanced topics."),
        CS3231("CS3231", "Theory of Computation",
            "This course examines fundamental aspects of computation that every computer scientist should know. What "
            + "is a finite automaton and how does it relate to regular expressions (and searching a database)? What "
            + "is a context-free language and how does it relate to parsing languages? What is the P vs. NP problem "
            + "and why does it matter? How do we decide if a problem is easy or hard? This course introduces "
            + "techniques for precisely formulating problems and studying their properties using a variety of "
            + "commonly used reasoning techniques (e.g., model equivalence, non-determinism, digitalisation, "
            + "simulation and reduction)."),
        CS3233("CS3233", "Competitive Programming",
            "This course aims to prepare students in competitive problem solving. It covers techniques for attacking "
            + "and solving challenging computational problems. Fundamental algorithmic solving techniques covered "
            + "include divide and conquer, greedy, dynamic programming, backtracking and branch and bound. Domain "
            + "specific techniques like number theory, computational geometry, string processing and graph theoretic "
            + "will also be covered. Advanced AI search techniques like iterative deepening, A* and heuristic search "
            + "will be included. The course also covers algorithmic and programming language toolkits used in problem"
            + " solving supported by the solution of representative or well-known problems in the various algorithmic"
            + " paradigms."),
        CS3234("CS3234", "Logic for Proofs and Programs",
            "This course introduces logic as a means for specifying and solving computational problems. It explores "
            + "how logic can be used to represent computational problems, how these representations can be proven "
            + "correct, and how they can be executed on a computer. Students learn about logic as formal systems "
            + "(semantic, axiomatic, and deductive) and how to write proofs in the different systems. They also learn"
            + " how to use a proof assistant such as Coq and how to program in a logic programming language such as "
            + "Prolog. Topics include classical and intuitionistic logic, SAT, Peano’s axioms, Hoare logic, and other"
            + " selected logic systems."),
        CS3235("CS3235", "Computer Security",
            "The objective of this course is to provide a broad understanding of computer security with some indepth "
            + "discussions on selected topics in system and network security. This course covers the following "
            + "topics: intrusion detection, DNS security, electronic mail security, authentication, access control, "
            + "buffer overflow, memory and stack protection, selected topics in application security, for instance, "
            + "web security, and well-known attacks."),
        CS3236("CS3236", "Introduction to Information Theory",
            "This module introduces the basics of modern information theory. It covers how information can be "
            + "quantified, and what this quantification tells us about how well we can compress and transmit "
            + "information without error. It discusses basic error correcting techniques, and information-theoretic "
            + "cryptography. Topics covered include mathematical techniques, entropy measures, fundamental limits to "
            + "data compression and noisy-channel coding, examples of error-correcting codes, examples of information"
            + " theoretic cryptography (commitments, secure computation, key distribution, randomness extraction)."),
        CS3237("CS3237", "Introduction to Internet of Things",
            "The Internet of Things (IoT), where a large number of physical objects embedded with computing power and"
            + " sensors connect to the network for seamless cooperation between the cyber domain and the physical "
            + "world, is revolutionizing our lives. This course will serve as an introduction to the IoT and provide "
            + "a holistic view of the entire spectrum of the IoT system architecture from the devices to the fog and "
            + "the cloud computing. The focus will be on designing IoT systems that balance both the functional and "
            + "non-functional (communication bandwidth, security, safety, power) requirements. The course will have a"
            + " significant project component."),
        CS3240("CS3240", "Interaction Design",
            "This course is intended for students in computing and related disciplines whose work focuses on "
            + "human-computer interaction issues in the design of computer systems. The course stresses the "
            + "importance of user-centred design and usability in the development of computer applications and "
            + "systems. Students will be taken through the analysis, design, development, and evaluation of "
            + "human-computer interaction methods for computer systems. They will acquire hands-on design skills "
            + "through laboratory exercises and assignments. The course also covers HCI design principles and "
            + "emphasizes the importance of contextual, organisational, and social factors in system design."),
        CS3241("CS3241", "Computer Graphics",
            "This course teaches some graphics hardware devices, reviews the mathematics related to the "
            + "understanding, and discusses the fundamental areas of computer graphics. After completing the course, "
            + "students are expected to understand the basic computer graphics terminology and concepts, and to be "
            + "able to design and implement simple 2D and 3D interactive computer graphics related programs. As an "
            + "enrichment part of the course, students are introduced the state-of-the-art development in computer "
            + "graphics by viewing interesting video clips and experimenting with demo program made available in the "
            + "course web."),
        CS3242("CS3242", "3D Modeling and Animation",
            "This course aims to provide fundamental concepts in 3D modeling and animation. It also serves as a "
            + "bridge to advanced media courses. After taking this course, students should be able to use these "
            + "concepts to easily build or work with digital models, manipulate the models by means of computer "
            + "deformation and animation, and use lighting and rendering techniques to create appealing scenes. "
            + "Topics include coordinate spaces, transforms, 3D model representations, hierarchical structures, "
            + "deformation, procedural modelling, particle systems, character animation, shading networks, lighting, "
            + "and scripting concepts."),
        CS3243("CS3243", "Introduction to Artificial Intelligence",
            "The course introduces the basic concepts in search and knowledge representation as well as to a number "
            + "of sub-areas of artificial intelligence. It focuses on covering the essential concepts in AI. The "
            + "course covers Turing test, blind search, iterative deepening, production systems, heuristic search, A*"
            + " algorithm, minimax and alpha-beta procedures, predicate and first-order logic, resolution refutation,"
            + " non-monotonic reasoning, assumption-based truth maintenance systems, inheritance hierarchies, the "
            + "frame problem, certainly factors, Bayes' rule, frames and semantic nets, planning, learning, natural "
            + "language, vision, and expert systems and LISP."),
        CS3244("CS3244", "Machine Learning",
            "This course introduces basic concepts and algorithms in machine learning and neural networks. The main "
            + "reason for studying computational learning is to make better use of powerful computers to learn "
            + "knowledge (or regularities) from the raw data. The ultimate objective is to build self-learning "
            + "systems to relieve human from some of already-too-many programming tasks. At the end of the course, "
            + "students are expected to be familiar with the theories and paradigms of computational learning, and "
            + "capable of implementing basic learning systems."),
        CS3245("CS3245", "Information Retrieval",
            "This course discusses the basic concepts and methods of information retrieval including capturing, "
            + "representing, storing, organizing, and retrieving unstructured or loosely structured information. The "
            + "most well-known aspect of information retrieval is document retrieval: the process of indexing and "
            + "retrieving text documents. However, the field of information retrieval includes almost any type of "
            + "unstructured or semi-structured data, including newswire stories, transcribed speech, email, blogs, "
            + "images, or video. Therefore, information retrieval is a critical aspect of Web search engines. This "
            + "course also serves as the foundation for subsequent courses on the understanding, processing and "
            + "retrieval of particular web media."),
        CS3247("CS3247", "Game Development",
            "The objective of this course is to introduce techniques for electronic game design and programming. This"
            + " course covers a range of important topics including 3D maths, game physics, game AI, sound, as well "
            + "as user interface for computer games. Furthermore, it will give an overview of computer game design to"
            + " the students. Through laboratory programming exercises, the students will have hands-on programming "
            + "experience with popular game engines and will develop basic games using those engines."),
        CS3249("CS3249", "User Interface Development",
            "This course aims at providing students with technical skills and hands-on experience of user interface "
            + "development. It focuses on the design and implementation of user interfaces in general, including "
            + "graphical user interface. It covers essential topics including user interface models, psychology of "
            + "humans and computers, user interface style, layout guidelines, GUI programming with widget toolkits, "
            + "interaction models, event handling, multithreading, interacting with multimedia hardware, usability "
            + "testing. Selected advanced topics such as geometric transformation, and 3D user interfaces, "
            + "multiple-user interaction and real-time interaction are also covered."),
        CS3263("CS3263", "Foundations of Artificial Intelligence",
            "Artificial intelligence is increasingly integrated in our everyday lives, and becoming a core engine to "
            + "power social and economic growth. This course covers the core concepts in artificial intelligence that"
            + " underlie solutions to these challenging, multi-facted problems. Topics covered include knowledge "
            + "representation and reasoning with uncertainty, preference, and time, constraint satisfaction, games "
            + "and optimized decisions, human factors, and new advances in integrative AI and responsible AI. The "
            + "goal of this class is to introduce students to a variety of approaches for solving real-life problems "
            + "using the tools of AI."),
        CS3264("CS3264", "Foundations of Machine Learning",
            "This course covers the fundamental concepts, theory, and algorithms in machine learning, and a variety "
            + "of modeling techniques to extract information from raw data. The class will cover topics in both "
            + "supervised and unsupervised learning, including problems in classification and regression, "
            + "computational learning theory, reinforcement learning, probabilistic inference, ensemble learning, and"
            + " more advanced topics. The class will cover both the underlying mathematical tools, as well as "
            + "practical frameworks for solving real-world problems. At the end of the course, students are expected "
            + "to be familiar with the theories and paradigms of computational learning, and capable of implementing "
            + "basic learning systems."),
        CS4211("CS4211", "Formal Methods for Software Engineering",
            "Before software can be designed, its requirements must be well understood. This in turns requires a "
            + "thorough understanding of the application domain. Based on the requirements, software engineers "
            + "construct design models, and then use these design models as guide to construct software "
            + "implementations. This course will cover formal specification and verification techniques for "
            + "accurately capturing and reasoning about requirements, model and code. The topics covered include "
            + "modeling notations, temporal logics, model checking, software model checking, theorem proving, and "
            + "symbolic execution based analysis. Most importantly, the course will attempt to inculcate an "
            + "appreciation and understanding of formal thinking in software design and construction."),
        CS4212("CS4212", "Compiler Design",
            "The objective of this course is to introduce the principal ideas behind program compilation, and "
            + "discusses various techniques for program parsing, program analysis, program optimisation, and run-time"
            + " organisation required for program execution. Topics covered include regular expressions, context-free"
            + " grammars, lexical analysis, syntax analysis; different algorithms for parsing codes, such as top-down"
            + " parsing, bottom-up parsing; translation to abstract syntax using modern parser generator technology, "
            + "intermediate representation, semantics analysis, type system, un-optimised code generation, code "
            + "optimisation, data-flow analysis, instruction scheduling."),
        CS4215("CS4215", "Programming Language Implementation",
            "This course provides the students with theoretical knowledge and practical skill in the implementation "
            + "of programming languages. It discusses implementation aspects of fundamental programming paradigms "
            + "(imperative, functional and object-oriented), and of basic programming language concepts such as "
            + "binding, scope, parameter-passing mechanisms and types. It introduces the language processing "
            + "techniques of interpretation and compilation and virtual machines. The lectures are accompanied by lab"
            + " sessions which will focus on language processing tools, and take the student through a sequence of "
            + "programming language implementations. This courses also covers automatic memory management, dynamic "
            + "linking and just-in-time compilation, as features of modern execution systems."),
        CS4218("CS4218", "Software Testing",
            "This course covers the concepts and practice of software testing including unit testing, integration "
            + "testing, and regression testing. Various testing coverage criteria will be discussed. Debugging "
            + "methods for finding the root-cause of errors in failing test cases will also be investigated. The use "
            + "of testing and analysis for performance prediction, performance clustering and performance debugging "
            + "will be studied. Students will acquire crucial skills on testing and debugging through hands-on "
            + "assignments."),
        CS4220("CS4220", "Knowledge Discovery Methods in Bioinformatics",
            "The advent of high throughput technologies (e.g., DNA chips, microarray), biologists are being "
            + "overloaded with information (e.g., gene expression data). A systematic way is needed to analyze the "
            + "data to make sense of them. This course is introduced to provide students with knowledge of techniques"
            + " that can be used to analyse biological data to enable them to discover new knowledge. At the end of "
            + "the course, students will be able to identify the relevant techniques for different biological data to"
            + " uncover new information. Topics include: Clustering analysis, classification, association rule "
            + "mining; support vector machines; Hidden Markov Models."),
        CS4222("CS4222", "Wireless Networking",
            "This course aims to provide solid foundation for students in the area of wireless networks and "
            + "introduces students to the emerging area of cyber-physical-system/Internet-of-Things. The course will "
            + "cover wireless networking across all layers of the networking stack including physical, link, MAC, "
            + "routing and application layers. Different network technologies with different characteristic will also"
            + " be covered, including cellular networks, Wi-Fi, Bluetooth and ZigBee. Some key concepts that cut "
            + "across all layers and network types are mobility management, energy efficiency, and integration of "
            + "sensing and communications. The course emphasizes on exposing students to practical network system "
            + "issues through building software prototypes."),
        CS4223("CS4223", "Multi-core Architectures",
            "The world of parallel computer architecture has gone through a significant transformation in the recent "
            + "years from high-end supercomputers used only for scientific applications to the multi-cores (multiple "
            + "processing cores on a single chip) that are ubiquitous in mainstream computing systems including "
            + "desktops, servers, and embedded systems. In the context of this exciting development, the aim of this "
            + "course is to examine the design issues that are critical to modern parallel architectures. Topics "
            + "include instruction-level parallelism through static and dynamic scheduling, shared memory, "
            + "message-passing, and data parallel computer architectures, cache coherence protocols, hardware "
            + "synchronization primitives, and memory consistency models."),
        CS4224("CS4224", "Distributed Databases",
            "This course studies the management of data in a distributed environment. It covers the fundamental "
            + "principles of distributed data management and includes distribution design, data integration, "
            + "distributed query processing and optimization, distributed transaction management, and replication. It"
            + " will also look at how these techniques can be adapted to support database management in emerging "
            + "technologies (e.g., parallel systems, peer-to-peer systems, cloud computing)."),
        CS4225("CS4225", "Big Data Systems for Data Science",
            "Data science incorporates varying elements and builds on techniques and theories from many fields, "
            + "including statistics, data engineering, data mining, visualization, data warehousing, and "
            + "high-performance computing systems with the goal of extracting meaning from big data and creating data"
            + " products. Data science utilizes advanced computing systems such as Apache Hadoop and Spark to address"
            + " big data challenges. In this course, students will learn various computing systems and optimization "
            + "techniques that are used in data science with emphasis on the system building and algorithmic "
            + "optimizations of these techniques."),
        CS4226("CS4226", "Internet Architecture",
            "This course aims to focus on advanced networking concepts pertaining to the modern Internet architecture"
            + " and applications. It covers a range of topics including network performance (throughput, delay, "
            + "Little’s Law and M/M/1 queuing formula), and resource allocation and buffer management (max-min fair, "
            + "round-robin and RED), intra- and inter-domain routing (RIP, OSPF and BGP), congestion control and "
            + "modern variations of TCP (AIMD and Cubic TCP), peer-to-peer applications and content delivery networks"
            + " (BitTorrent, Skype, Akamai), and data center networking and management (SDN and OpenFlow)."),
        CS4230("CS4230", "Foundations of Modern Cryptography",
            "This course introduces students to the theoretical underpinnings of cryptography Modern cryptographic "
            + "primitives can generate strings that look random, send messages that only intended recipients can "
            + "read, authenticate individuals, run computations on sensitive data without compromising privacy, and "
            + "perform several other seemingly paradoxical tasks We will study a number of these primitives with "
            + "emphasis on how to: (i) define their security requirements rigorously, (ii) construct them, and (iii) "
            + "prove that these constructions are secure We will focus on the theoretical foundations that they are "
            + "built upon, using tools from algebra, number theory, combinatorics, and probability. This will involve"
            + " rigorous mathematical"),
        CS4231("CS4231", "Parallel and Distributed Algorithms",
            "This course will examine some fundamental issues in parallel programming and distributed computing, and "
            + "the relationships between the two.  Parallel programming: mutual exclusion, semaphores, consistency, "
            + "wait-free synchronization.  Distributed computing: time, global state, snapshots, message ordering.  "
            + "Relationships: consensus, fault-tolerance, transactions, self-stabilization."),
        CS4234("CS4234", "Optimisation Algorithms",
            "This course covers common algorithmic techniques for solving optimisation problems, and introduces "
            + "students to approaches for finding good-enough solutions to NP-hard problems. Topics covered include "
            + "linear and integer programming, network flow algorithms, local search heuristics, approximation "
            + "algorithms, and randomized algorithms. Through analysis and application of the techniques to a variety"
            + " of canonical problems, students develop confidence to (i) appropriately model a given optimisation "
            + "problem, (ii) apply appropriate algorithmic techniques to solve the problem, (iii) analyse the "
            + "properties of the problem and candidate algorithms, such as time and space complexity, convergence, "
            + "approximability, and optimality bound."),
        CS4235("CS4235", "Computational Geometry",
            "Computational geometry is the study of algorithms for solving geometric problems. This course introduces"
            + " the main topics and techniques in this field. They will be presented in connection with applications "
            + "in CAD, databases, geographic information systems, graphics and robotics. Students will learn the main"
            + " algorithmic techniques for solving geometric problems and the related discrete geometric structures. "
            + "At the end of this course, students will be able to design and analyse geometric algorithms and data "
            + "structures, and to apply these techniques to solve problems arising in applications."),
        CS4236("CS4236", "Cryptography Theory and Practice",
            "This course aims to introduce the foundation, principles and concepts behind cryptology and the design "
            + "of secure communication systems. The emphasis is on the formulation and techniques of various "
            + "cryptographic primitives, and on the secure usage of such primitives to achieve the goals of "
            + "confidentially, integrity, and authenticity in both theoretical settings and practical scenarios. "
            + "Basic topics include pseudorandom functions, symmetric key encryption, public key encryption, message "
            + "authentication codes, hash functions, digital signatures, key exchange and PKI. Selected topics may "
            + "include: secret sharing, TCP/IP security, Kerberos, SSL, trusted computing, side-channel attacks."),
        CS4238("CS4238", "Computer Security Practice",
            "This is a practice security course with emphasis on hands-on experiences of computer security. The "
            + "objective of this course is to connect computer security knowledge to practical skills, including "
            + "common attacks and protection mechanisms, system administration, and development of secured software. "
            + "Topics covered include network security, operating system security, and application security, such as "
            + "DNS attacks, memory-error exploits, and web application attacks. Students will learn through lab-based"
            + " exercises and assignments."),
        CS4239("CS4239", "Software Security",
            "Software engineering processes need to include security considerations in the modern world. This course "
            + "familiarizes students to security issues in different stages of the software life-cycle. At the end of"
            + " the course, the students are expected to understand secure programming practices, be able to analyse "
            + "and check for impact of malicious inputs in programs, and employ specific testing techniques which can"
            + " help detect software vulnerabilities."),
        CS4240("CS4240", "Interaction Design for Virtual and Augmented Reality",
            "This course aims to expose students to the human-centered principles of designing and building virtual "
            + "reality (VR) and augmented reality (AR) applications. Students will learn about the fundamentals of VR"
            + " and AR, human perceptions of reality, and the design patterns and guidelines for user interactions "
            + "within VR/AR applications. Students will gain hands on experience building VR/AR applications applying"
            + " these interaction principles."),
        CS4242("CS4242", "Social Media Computing",
            "The emergence of WWW, smart mobile devices and social networks has revolutionised the way we "
            + "communicate, create, disseminate, and consume information. This has ushered in a new era of "
            + "communications that involves complex information exchanges and user relationships. This course aims to"
            + " provide students with a good understanding of the social network phenomena and computational skills "
            + "for analysing the complex social relation networks between users, the contents they shared, and the "
            + "ways contents and events are perceived and propagated through the social networks. The analysis will "
            + "provide better understanding of the concerns and interests of users, and uncover live and emerging "
            + "events that will affect the community."),
        CS4243("CS4243", "Computer Vision and Pattern Recognition",
            "This course is for undergraduates who are interested in computer vision and its applications. It covers "
            + "(a) the basic skills needed in handling images and videos, (b) the basic theories needed to understand"
            + " geometrical computer vision, and (c) pattern recognition. Topics covered in image handling include: "
            + "contrast stretch, histogram equalization, noise removal, and color space. Topics covered in "
            + "geometrical vision include: affine transform, vanishing points, camera projection models, homography, "
            + "camera calibration, rotation representations including quaternions, epipolar geometry, binocular "
            + "stereo, structure from motion. Topics covered for pattern recognition include principal component "
            + "analysis."),
        CS4244("CS4244", "Knowledge Representation and Reasoning",
            "This course will focus on core issues of representation and reasoning of the knowledge in the context of"
            + " design of intelligent machines. We will focus on how logic can be used to formalise and perform "
            + "deduction from existing knowledge. We will then discuss compilation techniques. Finally, we will "
            + "discuss limitations of monotonic logic and discuss challenges for non-monotonic reasoning."),
        CS4246("CS4246", "AI Planning and Decision Making",
            "This course introduces the major concepts and paradigms in planning and decision making in complex "
            + "environments. It examines issues, challenges, and techniques in problem representation, goal or "
            + "objective specification, response selection, and action consequence for a wide range of strategic and "
            + "tactical planning and decision making situations. Topics covered include deterministic and "
            + "non-deterministic planning, practical planning and acting under resource constraints and uncertainy, "
            + "expected utility and rational decision making, decision networks, Markov decision processes, "
            + "elementary game theory, and multi-agent planning and decision making."),
        CS4247("CS4247", "Graphics Rendering Techniques",
            "This course provides a general treatment of real-time and offline rendering techniques in 3D computer "
            + "graphics. Specific topics include the raster graphics pipeline, viewing and transformation, real-time "
            + "mapping techniques, real-time shadow algorithms, local reflection models, global illumination, "
            + "distributed ray tracing, photon mapping, radiosity, volume rendering, image-based rendering and "
            + "modelling, and strategies for anti-aliasing and photo-realism."),
        CS4248("CS4248", "Natural Language Processing",
            "This course deals with computer processing of human languages, including the use of neural networks and "
            + "deep learning in natural language processing. The topics covered include: regular expressions, words "
            + "and edit distance, n-grams, part-of-speech tagging, feed-forward neural networks, neural network "
            + "training, word embedding, convolutional neural networks, recurrent neural networks, "
            + "sequence-to-sequence models with attention, transformers, context-free grammars, syntactic parsing, "
            + "semantics, and discourse."),
        CS4249("CS4249", "Phenomena and Theories of Human-Computer Interaction",
            "This course teaches the underlying science of Human-Computer Interaction (HCI) and its application to "
            + "user interface design. It surveys a wide range of psychological theories beginning with organizational"
            + " behaviour approaches, understanding of work and workflow within organizations, and moving on to "
            + "understanding human psychological architecture and processing constraints. It demonstrates via a "
            + "combination of scientific theory understanding and engineering modelling the solutions of design "
            + "problems facing a user interface designer. It also covers new design methods and techniques available "
            + "and the new conceptual mechanisms used in HCI such as the metaphors for describing user interaction."),
        CS4261("CS4261", "Algorithmic Mechanism Design",
            "Recent years have seen a dramatic rise in the use of algorithms for solving problems involving strategic"
            + " decision makers. Deployed algorithms now assist in a variety of economic interactions: assigning "
            + "medical residents to schools, allocating students to courses, allocating security resources in "
            + "airports, allocating computational resources and dividing rent. We will explore foundational topics at"
            + " the intersection of economics and computation, starting with the foundations of game theory: Nash "
            + "equilibria, the theory of cooperative games, before proceeding to covering more advanced topics: "
            + "matching algorithms, allocation of indivisible goods, and mechanism design."),
        CS4268("CS4268", "Quantum Computing",
            "This course will introduce basics of quantum computing and cover various well known algorithms e.g. "
            + "Deutsch-Jozsa algorithm, Simon’s algorithms, quantum Fourier transform, phase estimation, order "
            + "finding, Shor’s algorithm and Grover’s algorithm. The course will also cover some basics in quantum "
            + "information theory, cryptography and error correction."),
        CS4269("CS4269", "Fundamentals of Logic in Computer Science",
            "Logic is often called the \"calculus of computer science\" due to the central role played by logic in "
            + "computer science akin to the role played by calculus in physics and engineering. This course will "
            + "formally introduce and prove some of the fundamental results in logic to provide students with a "
            + "rigorous introduction of syntax, semantics, decision procedures, and complexity of propositional and "
            + "first-order logic. The course will be taught from a computer science perspective with particular "
            + "emphasis on algorithmic and computational complexity components."),
        CS4277("CS4277", "3D Computer Vision",
            "One of the most important capability for robots such as self-driving cars, domestic mobile robots, and "
            + "drones to achieve full autonomy is the ability to perceive the 3D environment. A camera is an "
            + "excellent choice as the main sensory device for robotic perception because it produces "
            + "information-rich images, and is lightweight, low cost and requires little or no maintenance. This "
            + "course covers mathematical concepts and algorithms that allow us to recover the 3D geometry of camera "
            + "motions and the structures in its environment. Topics include projective geometry, camera model, "
            + "one-/two-/three-/N-View reconstructions and stereo, generalized cameras and non- rigid "
            + "structure-from-motion."),
        CS4278("CS4278", "Intelligent Robots: Algorithms and Systems",
            "This course introduces the core algorithms and system architectures of intelligent robots. It examines "
            + "the main system components for sensing, decision making, and motion control and importantly, their "
            + "integration for core robot capabilities, such as navigation and manipulation. It covers the key "
            + "algorithms for robot intelligence through inference, planning, and learning, and also provides some "
            + "practical experiences with modern robot systems. A variety of Illustrative examples are given, e.g., "
            + "self-driving cars, aerial drones, and object manipulation."),
        CS4345("CS4345", "General-Purpose Computation on GPU",
            "With the advancements in the technology of graphics processing units (GPUs), many computations can be "
            + "performed faster on the GPUs than the CPUs. They are also programmable, making them useful for not "
            + "just computer graphics processing but also general-purpose computations. Therefore, they are a natural"
            + " choice as high-speed coprocessors to the CPUs in various applications. This course introduces the "
            + "architecture of GPU, program writing on GPU using high-level language such as Cg, and the use of GPU "
            + "in applications including computer graphics, games, scientific computation, and image processing."),
        CS4347("CS4347", "Sound and Music Computing",
            "This course introduces the fundamental technologies employed in Sound and Music Computing focusing on "
            + "three major categories: speech, music, and environmental sound. This course introduces the concept of "
            + "sound and its representations in the analog and digital domains, as well as in time and frequency "
            + "domains. Moreover, this course provides hands-on instruction on relevant machine learning tools, and "
            + "an in-depth review of related technologies in sound data analytics (Automatic Speech Recognition, "
            + "Automatic Music Transcription and Sound Event Detection) leading to a group project. Topics in sound "
            + "synthesis, automatic music generation and music information retrieval will be covered for breadth."),
        CS4880("CS4880", "Digital Entrepreneurship",
            "The course will cover trends in the digital marketplace and emerging high-growth opportunities for "
            + "digital businesses. The course will highlight issues facing companies with new products and services "
            + "in an ever-changing digital marketplace. While the course will provide an overview on structuring of "
            + "new ventures, the primary focus will be on opportunity identification and sources of competitive "
            + "differentiation, particularly as they relate to digital innovation. To hone these skills the students "
            + "will communicate by crafting a business plan."),
        CS5223("CS5223", "Distributed Systems",
            "The topic of Distributed Systems is now garnering increasing importance, especially with the advancement"
            + " in technology of the Internet and WWW. The aim of this course is to provide students with basic "
            + "concepts and principles of distributed operating systems, interprocess communications, distributed "
            + "file systems, shared data, and the middleware approach. The course is taught in seminar style, and "
            + "several case studies are included, e.g. CORBA. Topics: Introduction - Characteristics of Distributed "
            + "Systems; Process Management Communication in Distributed Systems; Distributed Synchronisation; "
            + "Distributed Real-time Systems; File Systems; Naming Security; Fault Tolerant Distributed Systems; "
            + "Distributed Simulation; WWW as an application of Distributed System."),
        CS5224("CS5224", "Cloud Computing",
            "This course aims to provide an overview of the design, management and application of cloud computing. "
            + "The topics include managing virtualization, cloud computing environments, cloud design patterns and "
            + "use cases, data centre architectures and technologies, cloud services fulfillment and assurance, "
            + "orchestration and automation of cloud resources, cloud capacity management, cloud economics, case "
            + "studies."),
        CS5228("CS5228", "Knowledge Discovery and Data Mining",
            "This course introduces fundamental principles behind data mining and efficient techniques for mining "
            + "large databases. It provides an overview of the algorithmic aspect of data mining: its efficiency "
            + "(high-dimensional database indexing, OLAP, data reduction, compression techniques) and effectiveness "
            + "(machine learning involving greedy search, branch and bound, stochastic search, parameter "
            + "optimisation). Efficient techniques covered include association rules mining (Apriori algorithm, "
            + "correlation search, constrained association rule discovery), classifier induction (decision trees, "
            + "RainForest, SLIQ; Support vector machine; Naive Bayesian; classification based on association / "
            + "visualisation), cluster analysis (k-means, k-mediods, DBSCAN, OPTICS, DENCLUE, STING, CLUSEQ, ROCK "
            + "etc), and outliers/deviants detection (LOF, Distance-based outlier etc)."),
        CS5229("CS5229", "Advanced Computer Networks",
            "This course covers advanced fundamental principles of computer networks and techniques for networking. "
            + "The goal of this course is to teach these fundamentals/techniques that will remain important and "
            + "relevant regardless of the hot topics in networks and networking. Briefly, the topics include advanced"
            + " network architecture and design principles, protocol mechanisms, implementation principles and "
            + "software engineering practices, network algorithmic, network simulation techniques and tools, "
            + "performance analysis and measurement, and protocol specification/verification techniques."),
        CS5231("CS5231", "Systems Security",
            "This course introduces fundamental notions and requirements in computer system security and the "
            + "mechanisms that provide security in various systems and applications. It aims to teach students the "
            + "security perspective of popular computer systems, such as desktop systems, mobile systems, and "
            + "web-based systems. Its topics include software security, operating system security, mobile security, "
            + "web security, trusted platforms, and auditing and forensic analysis."),
        CS5232("CS5232", "Formal Specification and Design Techniques",
            "The primary role of the formal specification is to provide a precise and unambiguous description of a "
            + "computer system. A formal specification allows the system designer to verify important properties and "
            + "detect design error before system development begins. The objective of this course is to study various"
            + " formal specification and design techniques for modelling (1) object-oriented systems, (2) real-time "
            + "distributed systems, and (3) concurrent reactive systems. The course will focus on the state-based "
            + "notations Z/Object-Z, event-based notation CSP/Timed-CSP. Graphical modelling notations, such as "
            + "StateChart and UML (Unified Modelling Language) will also be addressed."),
        CS5234("CS5234", "Algorithms at Scale",
            "This course presents advanced techniques for the design and analysis of algorithms and data structures, "
            + "with emphasis on efficiency and scalability. It will cover a variety of algorithmic topics that arise "
            + "when coping with very large data sets. How do you design algorithms that scale well? How do you "
            + "process streaming data? How do you construct algorithms that run efficiently on modern hardware? The "
            + "goal of this course is to cover modern tools and techniques in algorithm design."),
        CS5237("CS5237", "Computational Geometry and Applications",
            "The course aims to provide students with a geometric viewpoint in problem solving. It lays a foundation "
            + "for solving problems with computational geometric methods, and bridges the gap between theoretical "
            + "computer science and the real applications by introducing application areas, such as bio-geometric "
            + "modelling, computer graphics and mesh generation, as well as other engineering problems such as "
            + "reverse engineering. Topics include: convex-hull algorithms, simplicial complexes, union of balls, "
            + "Voronoi diagram, Delaunay triangulation, lifting and projecting, alpha shape, surface reconstruction, "
            + "sphere algebra, orthogonality and complementarity, molecular skin surfaces, curvatures and surface "
            + "meshing, deformation and morphing, etc."),
        CS5239("CS5239", "Computer System Performance Analysis",
            "The objective of this course is to provide students a working knowledge of computer performance "
            + "evaluation and capacity planning. They will be able to identify performance bottlenecks, to predict "
            + "when performance limits of a system will be exceeded, and to characterise present and future workload "
            + "to perform capacity planning activities. Topics include: performance analysis overview; measurement "
            + "techniques and tools including workload characterisation, instrumentation, benchmarking, analytical "
            + "modelling techniques including operational analysis, stochastic queuing network analysis; performance "
            + "of client-server architectures; capacity planning; case studies."),
        CS5241("CS5241", "Speech Processing",
            "This course exposes the graduate students to the fundamental theory of speech processing, focusing "
            + "primarily on automatic speech recognition. Upon completion of this course, students should be able to "
            + "perform research on speech recognition topics and commercial speech technology development. Topics "
            + "covered by this course include: speech signal processing, automatic speech recognition (ASR), "
            + "continuous speech recognition, acoustic modelling using the Hidden Markov Model (HMM), language "
            + "modelling for ASR and advanced speech recognition techniques for state-of-the-art large vocabulary "
            + "continuous speech recognition (adaptation and robustness, discriminative training and decoding "
            + "strategies)."),
        CS5242("CS5242", "Neural Networks and Deep Learning",
            "This course provides students with the knowledge of deep neural network and enables them to apply deep "
            + "learning methods effectively on real world problems. The course emphasizes on the understanding of the"
            + " principles of neural networks and deep learning; practical guidelines and techniques for deep "
            + "learning; and their applications. Through assignments and projects, students will design, develop, and"
            + " evaluate deep learning-based solutions to practical problems, such as those in the areas of computer "
            + "vision, bioinformatics, fintech, cybersecurity, and games."),
        CS5246("CS5246", "Text Mining",
            "Text mining concerns the processing of unstructured natural language text to mine information and "
            + "knowledge. It is distinguished from data mining in its focus on unstructured text rather than "
            + "structured data present in traditional databases. Topics include text classification, text clustering,"
            + " sentiment analysis, text summarization, information extraction (named entity recognition, relation "
            + "and event extraction), and question answering. The course will emphasize the use of machine learning "
            + "approaches to text mining."),
        CS5250("CS5250", "Advanced Operating Systems",
            "The course covers a broad range of issues in the design and implementation of modern advanced operating "
            + "systems. The topics covered in this course includes OS design strategies (including microkernels, "
            + "mobile, embedded and real-time operating systems and the component’s interfaces), priority and "
            + "resource allocation strategies; scheduling algorithms (including for multi-core, multi-processors); "
            + "naming, protection and security; UI and windowing systems; file system implementations (including "
            + "network and distributed file systems); failure and recovery; and virtualization and the Internet-ready"
            + " OS. They extend and provide in-depth coverage of material in earlier prerequisite OS courses."),
        CS5272("CS5272", "Embedded Software Design",
            "This course focuses on the design and implementation of software for programmable embedded systems. "
            + "Embedded computing systems hidden inside everyday electronic devices such as hand-phones, digital "
            + "cameras etc. are becoming more and more prevalent. However, the heterogeneous nature of the underlying"
            + " hardware as well as tight constraints on size, cost, power, and timing pose significant challenges to"
            + " embedded software development. This course presents techniques that address these distinctive "
            + "characteristics of embedded software implementation. Topics include embedded software development for "
            + "programmable processors and reconfigurable hardware, component-based design, optimizations for "
            + "performance, power, code size, operating system issues, and case studies of available systems."),
        CS5321("CS5321", "Network Security",
            "The objective of this course is to introduce students to the various issues that arise in securing the "
            + "networks, and study the state-of-the-art techniques for addressing these challenges. A number of most "
            + "damaging attacks on computer systems involve the exploitation of network infrastructure. This course "
            + "provides an in-depth study of network attack techniques and methods to defend against them. Topics "
            + "include basic concepts in network security; firewalls and virtual private networks; network intrusion "
            + "detection; denial of service (DoS); traffic analysis; secure routing protocols; protocol scrubbing; "
            + "and advanced topics such as wireless network security."),
        CS5330("CS5330", "Randomized Algorithms",
            "The course will cover basic concepts in the design and analysis of randomized algorithms. It will cover "
            + "both basic techniques, such as Chernoff bounds, random walks, and the probabilistic method, and a "
            + "variety of practical algorithmic applications, such as load balancing, hash functions, and "
            + "graph/network algorithms. The focus will be on utilizing randomization to develop algorithms that are "
            + "more efficient and/or simpler than their deterministic counterparts."),
        CS5331("CS5331", "Web Security",
            "This course aims to prepare graduate students for understanding the security of the latest web platform "
            + "and its interplay with operating systems and the cloud infrastructure. The topics covered include the "
            + "design of web browsers and web applications, vulnerabilities in web applications and web browsers, "
            + "design of web scanners, authentication in web-based platforms, security policies and enforcement "
            + "mechanisms. This course also covers security topics on the interface between the web platform and the "
            + "backend systems, such as the underlying database systems and cloud infrastructure."),
        CS5332("CS5332", "Biometric Authentication",
            "Biometrics (such as fingerprint, iris images) are commonly used for authentication. This course covers "
            + "authentication methods, different types of biometrics, pattern recognition, performance measurement, "
            + "spoofing attacks, as well as issues such as privacy, user acceptance, and standards compliance. "
            + "Students will gain a solid understanding of the fundamentals of the technology underlying biometric "
            + "authentication, and the key issues to be addressed for successful deployment. Both the theoretical and"
            + " practical aspects of biometrics authentication will be discussed."),
        CS5339("CS5339", "Theory and Algorithms for Machine Learning",
            "The course aims to provide a broad theoretical understanding of machine learning and how the theory "
            + "guides the development of algorithms and applications. Topics covered include the approximation "
            + "capabilities of common function classes used for machine learning, such as decision trees, neural "
            + "networks, and support vector machines, the sample complexity of learning different function classes "
            + "and methods of reducing the estimation error such as regularization and model selection, and "
            + "computational methods used for learning such as convex optimization, greedy methods, and stochastic "
            + "gradient descent."),
        CS5340("CS5340", "Uncertainty Modelling in AI",
            "The course covers modelling methods that are suitable for reasoning with uncertainty. The main focus "
            + "will be on probabilistic models including Bayesian networks and Markov networks. Topics include "
            + "representing conditional independence, building graphical models, inference using graphical models and"
            + " learning from data. Selected applications in various domains such as speech, vision, natural language"
            + " processing, medical informatics, bioinformatics, data mining and others will be discussed."),
        CS5343("CS5343", "Advanced Computer Animation",
            "From the covert digital water in Titanic to the mixed real and virtual actors in Avatar, from the "
            + "arm-controllable Wii games to the completely full-body-play Xbox Kinect games, computer animation "
            + "technologies have advanced significantly during the past decades, both in the movie and the game "
            + "industries. This course reveals all the exciting behind-the-scene techniques that make these movies "
            + "and games possible, including but not limited to motion capture, fluid animation, facial animation, "
            + "and fullbody character animation. This is a project-based course that aims to provide strong "
            + "foundation on advanced computer animation methods and prepare students for research in animation."),
        CS5346("CS5346", "Information Visualisation",
            "This course aims to bring together individual pedagogies of design, information, and computation, for "
            + "teaching the analysis and representation of data for visualisation. Students will learn the "
            + "methodology of developing and evaluating an information visualisation solution, common information "
            + "visualisation techniques (such as those for topical, spatial, hierarchical, temporal, and relational "
            + "data), and methods for scaling up interactive visualisation with big data. After the course, students "
            + "should be able to use the existing visualisation tools for building useful, interactive, information "
            + "visualisation to facilitate complex data analytics, exploration, understanding, and pattern discovery."),
        IFS4101("IFS4101", "Legal Aspects of Information Security",
            "This course examines the laws relating to information security. The issues and considerations concerning"
            + " information security have greatly shaped many laws, in particular, the laws relating to cybercrimes, "
            + "electronic commerce, electronic evidence, document discovery, information management and data "
            + "protection. These areas of the law have in turn altered the development and practice of information "
            + "security in the industry. The objective of this course is to provide information security "
            + "professionals with a working knowledge of these legal issues in information security, so that they are"
            + " better placed to represent and protect the legal interests of their employers and their institutions."),
        IFS4102("IFS4102", "Digital Forensics",
            "Digital forensics encompasses the recovery and investigation of material found in digital devices in "
            + "relation to cyber crime and other crimes where digital evidence is relevant. This course gives an "
            + "introduction to principles, techniques, and tools to perform digital forensics. Students will gain a "
            + "good understanding of the fundamentals of digital forensics; key techniques for performing evidence "
            + "extraction and analysis on UNIX/Linux systems, Windows systems, networks, Web applications, and mobile"
            + " devices; and gain exposure to available tools. Some legal aspects of digital forensics will also be "
            + "discussed."),
        IFS4103("IFS4103", "Penetration Testing Practice",
            "This is a practice-oriented and project-based course that provides a hands-on experience of performing "
            + "penetration testing on a collaborating organisation’s system. It aims to provide students with a "
            + "realistic platform for applying offensive-based vulnerability assessment and analysis techniques on "
            + "designated target systems. Students will be part of a penetration testing team, and be guided to apply"
            + " the methodology, techniques, and tools of assessing the security of the target systems. This course "
            + "contains a mix of technical-review seminars, testing-scoping meetings, and penetration testing "
            + "exercises, analysis, as well as reporting."),
        IS1103("IS1103", "Ethics in Computing",
               "This module gives an introduction to Ethics in the Computing domain. Students will learn about the "
                   + "importance of Ethics in Computing policy-making and be able to make judgements and decisions "
                   + "based on established ethical frameworks (such as Deontology, Consequentialism, Social Contract "
                   + "Theory and Virtue Ethics). The objective is to develop students to be ethical computing "
                   + "decision-makers who can analyse and explain their decisions in real-world policy-making "
                   + "situations. Issues in emerging areas such as Digital Intellectual Property Rights, Artificial "
                   + "Intelligence, Big Data, Social Media, Hacking, and interface design may also be discussed in "
                   + "relation to Ethics."),
        IS1108("IS1108", "Digital Ethics and Data Privacy",
            "Course is designed to introduce students to the issues of digital ethics and data privacy faced by "
            + "individuals and organizations. It covers the ethical principles governing the behaviors and beliefs "
            + "about how we use technology, and how we collect and process personal information in a manner that "
            + "aligns with individual and organizational expectations for security and confidentiality. It will "
            + "address challenges in balancing technological desirability with social desirability while developing "
            + "digital products and services, including Professional Ethics, Computing for Social Good, Digital "
            + "Ethics by Design, Digital Intellectual Property Rights, Automation and Autonomous Systems, and "
            + "Artificial Intelligence Ethics and Governance."),
        IS1128("IS1128", "IT, Management and Organisation",
            "This course introduces students to information technologies (IT) in organisations and the interplay "
            + "between IT, work, management, and organisations. Specifically, the course will examine the impacts of "
            + "modern IT and the related artificial intelligence (AI) technologies on knowledge workers, teamwork, "
            + "work design, management practices and the organization. Students will learn about the multifaceted "
            + "roles that IT can play to support communication, collaboration and organizational improvements in "
            + "operations, planning and decision making. They will also learn to apply strategic thinking to identify"
            + " opportunities for IT-enabled innovations and issues involving information systems (IS) adoption and "
            + "deployment."),
        IS2102("IS2102", "Enterprise Systems Architecture and Design",
            "This course aims to train students to be proficient in architecting and designing modern large-scale "
            + "Enterprise Systems that are complex, scalable, distributed, component-based and mission-critical. "
            + "Students will develop an in-depth understanding of high-level concepts such as enterprise architecture"
            + " and software architecture. They will then move on to acquire fundamental systems analysis and design "
            + "techniques such as object-oriented requirements analysis and design using the Unified Modelling "
            + "Language as well as software design patterns. Essential systems engineering skillsets such as software"
            + " testing and software configuration management will also be covered."),
        IS2218("IS2218", "Digital Platforms for Business",
            "Digital platforms underlie nearly all modern industries and businesses. Their interconnected ecosystems "
            + "radically transform the way we work, shop, transact, socialize and travel. This course aims to "
            + "introduce the phenomena of digital platforms for businesses and the associated business fundamentals, "
            + "concepts and models that underlie and support the business and value propositions of these platforms. "
            + "Specifically, students will learn about different digital marketing and retailing platforms (e.g., "
            + "Google, Amazon), fintech platforms (e.g., Binance, Alipay), and logistics/delivery platforms (e.g., "
            + "NinjaVan, Deliveroo), as well as basic marketing, finance and operations management concepts."),
        IS3103("IS3103", "Information Systems Leadership and Communication",
            "Today’s technology leaders must possess the ability to identify key drivers of business innovation and "
            + "be able to align technologies with business objectives. This course encompasses the strategic, "
            + "tactical, and operational aspects of managing and leading IT adoption, implementation, and "
            + "organizational change to derive strategic value from IT. Students will also learn influential, "
            + "strategic, and change communication skills, to foster collaboration between technology and business "
            + "stakeholders. By synthesising critical knowledge areas in information systems management and "
            + "leadership communication, students will examine the intertwined issues of technology and business for "
            + "leading digital transformations."),
        IS3106("IS3106", "Enterprise Systems Interface Design and Development",
            "This course aims to train students to be conversant in front-end development for Enterprise Systems. It "
            + "complements IS2103 which focuses on backend development aspects for Enterprise Systems. Topics covered"
            + " include: web development scripting languages, web templating design and component design, integrating"
            + " with backend application, and basic mobile application development."),
        IS3107("IS3107", "Data Engineering",
            "This course covers the main concepts of data engineering, which covers data pipeline, data organization,"
            + " efficient analysis of large data volumes, distributed data storage (depending on the system "
            + "architecture, e.g., multi-core systems, multiprocessor systems, clusters), distributed and parallel "
            + "data analysis, and map / reduce techniques and their generalization to distributed query processing."),
        IS3150("IS3150", "Digital Media Marketing",
            "This course prepares students with a foundational understanding of marketing through digital media and "
            + "technologies. Students will learn how marketing concepts, market analysis and consumers’ behavior are "
            + "impacted by shifting technology and media landscape. The course will cover fundamental concepts in "
            + "innovation, branding, customer development, search engine, social media, storytelling, display, "
            + "retargeting, customer journey, lifetime value, public relation and other related areas. Case studies "
            + "and hands on experience will be provided. At the end of the course, students will be better equipped "
            + "to deliver coherent digital marketing strategies."),
        IS3221("IS3221", "ERP Systems with Analytics Solutions",
            "Business resources include employees, business processes, procedures, organisational structure, and "
            + "computer systems. The efficiency and effectiveness of an organisation in carrying out its business can"
            + " be enhanced if managers and employees are given the support to plan, monitor and control the business"
            + ". Enterprise Resource Planning (ERP) supports the use of all resources in an organisation. This course"
            + " provides an overview of the rationale for having ERP, ERP functionality (such as manufacturing, "
            + "finance, distribution and human resource management), ERP systems and ERP implementation (planning, "
            + "product selection, implementation and optimization), and business analytic features on integrated "
            + "transactional and analytics platforms."),
        IS3240("IS3240", "Digital Platform Strategy and Architecture",
            "Digital business platforms are a successful foundation on which organisations are reinventing their "
            + "businesses. In this course, students will learn both theoretical and practical insights into the "
            + "dynamics of creating, implementing and competing with digital platforms, focused on three pillars. The"
            + " course will first examine the strategic and economic foundations of digital platforms (Pillar 1), and"
            + " their prominent rise in the digital transformation of industries, business models, products and "
            + "services. The course will then explore the business architecture and strategy (Pillar 2) and IT/data "
            + "architecture and strategies (Pillar 3) that enable platforms and their successful evolution. Topics "
            + "include platform principles"),
        IS3251("IS3251", "Principles of Technology Entrepreneurship",
            "The course introduces students to the concepts and principles of technology entrepreneurship. Students "
            + "will learn about the current developments in entrepreneurship, worldwide and in Singapore and be "
            + "taught to use a variety of tools, techniques and frameworks for the development and analysis of "
            + "entrepreneurial businesses. Students taking the course should have an interest in entrepreneurship and"
            + " a desire to be an entrepreneur at some stage in their lives."),
        IS4100("IS4100", "IT Project Management",
            "This course focuses on the management of IS projects. Various managerial issues pertaining to the "
            + "evaluation and selection of information systems projects, choice of project organization, planning, "
            + "scheduling and budgeting of project activities and basic principles in control and project auditing "
            + "will be covered. The students will also learn how to use practical techniques and tools, such as "
            + "network models (PERT/CPM), simulation, and state-of-the-art project management software, in scheduling"
            + " project activities. This course serves as a good introduction to information systems project "
            + "management for students who may participate in coordinating and managing large-scale information "
            + "systems projects."),
        IS4151("IS4151", "AIoT Solutions and Development",
            "AIoT (Artificial Intelligence of Things) is the integration of Artificial Intelligence (AI) technologies"
            + " with Internet of Things (IoT) infrastructure. AIoT aims to create systems that can complete a set of "
            + "tasks or make decisions autonomously and intelligently through learning from data. Such smart systems "
            + "can drive efficiency, effectiveness and innovation across a wide range of industries such as retail, "
            + "logistics and transportation. Students will learn how to build IoT infrastructure with edge computing "
            + "and cloud computing architectures for collecting sensor data and to apply basic machine learning "
            + "algorithms to these data to train models that power smart systems."),
        IS4152("IS4152", "Affective Computing",
            "This course provides a broad introduction to the field of affective computing, focusing on the "
            + "integration of psychological theories of emotion with the latest technologies. Students can look "
            + "forward to learning about contemporary theories of emotion, empathy, emotion regulation; automated "
            + "emotion recognition from video, speech, and text; automated affect generation in human-computer "
            + "interaction; commercial affective computing technologies, including potential interaction with local "
            + "startups. Students will work in groups on a semester-long project that may take several forms, such as"
            + " the incorporation of emotion recognition into a prototype system, or critical evaluation of "
            + "commercial affective computing technologies."),
        IS4204("IS4204", "IT Governance",
            "This course examines the governance in the use and deployment of Information Technology in an "
            + "organisation. It covers the process of strategic planning to align IT strategies with business "
            + "strategies. The elements of governance include Security Policy, Quality Management, Business "
            + "Continuity Management, Risk Management, Project and Program Management, Returns on Investment of IT "
            + "and Operational Management."),
        IS4226("IS4226", "Systematic Trading Strategies and Systems",
            "The course aims to provide students with the fundamentals of financial market systematic trading. It "
            + "covers the difference between discretionary and systematic systems, and the advantages and the need "
            + "for systematic systems. The course will emphasize the design and development of both the trading "
            + "strategies and the trading systems with the help of technical analysis and programming languages. It "
            + "will focus on rule-based systematic trading, both manual and through algorithms. It will also cover "
            + "investor biases and how to overcome those biases through data and systems to make better trading "
            + "decisions and to have proper risk management."),
        IS4228("IS4228", "Information Technologies in Financial Services",
            "The main objective of this course is to educate the students on how and to what extent can information "
            + "technologies (IT) support the financial services industry, in order for a student to seek a career in "
            + "this industry sector. It is designed to provide the students with a broad overview and thematic case "
            + "studies of how each major business segment of the financial services industry employs IT to maintain a"
            + " competitive edge, and to comply with laws and regulations."),
        IS4231("IS4231", "Information Security Management",
            "The main focus of this course is on the managerial aspects of information security. This course prepares"
            + " the students for their future roles as IS managers or IS security professionals. Through this course,"
            + " students will appreciate the challenges of managing information security in the modern business "
            + "organization. Topics include risk management, security policies and programmes, managing the security "
            + "function, and planning for continuity."),
        IS4233("IS4233", "Legal Aspects of Information Technology",
            "This course studies important legal issues relevant to professionals in IT-intensive industries or "
            + "organizations. Among the topics to be addressed are freedom of speech online; intellectual property of"
            + " digital products and content; data privacy, IT service contracts, and regulation on e-commerce and "
            + "intermediary, such as online marketplace and platform. The goal of the course is to provide basic "
            + "background in these issues for non-lawyers. The course enables IT professionals to better handle their"
            + " legal resources and better understand their commercial opportunities and challenges. Real-world cases"
            + " will be used to demonstrate the applicability of the law in IT industries."),
        IS4234("IS4234", "Governance, Regulation, and Compliance Technology",
            "Organizations today need to be aware of and ensure that they comply with all relevant laws and "
            + "regulations. At the same time, regulators are faced with challenges of enforcing compliance. Digital "
            + "technology can be used to facilitate both organizational compliance and regulatory enforcement of "
            + "compliance. Additionally, increasing use of IT requires governance and regulation of technology itself"
            + ". This course allows students to gain an understanding of how IT can be governed and regulated, as "
            + "well as how it can be applied for compliance and regulation, for example in the financial sector."),
        IS4236("IS4236", "Cloud Services and Infrastructure Management",
            "This course aims to equip students with the knowledge and skills to manage the modern hybrid multi-cloud"
            + " IT infrastructure as the backbone of the systems and processes that supports a company’s business "
            + "strategy, technological innovation ability, and digital capabilities. Students will also learn the key"
            + " components and needs of the modern cloud infrastructure so as to be able to design, deploy and manage"
            + " cloud services and enterprise solutions on it."),
        IS4240("IS4240", "Business Intelligence Systems",
            "Business Intelligence (BI) is the application of data base and machine learning technologies in business"
            + ". It enables organisations to improve decision making, enhance strategic position, and maintain "
            + "competitive advantage. This course will introduce students to the essentials of BI, placing emphasis "
            + "on database and machine learning technologies for building effective BI Systems. Students will learn "
            + "about data warehousing and data visualisation, as well as the various tools that can be employed for "
            + "intelligent business decision making. BI cases will be used to highlight the issues and problems "
            + "encountered by organisations as they developed and implemented BI systems."),
        IS4241("IS4241", "Social Media Network Analysis",
            "The world of online social media is of much interest for academic, social and e-commerce studies. This "
            + "course is about the analysis of social media networks. The course will cover the characteristics of "
            + "social media networks, the analysis software and methods, case studies and projects of network "
            + "analysis."),
        IS4242("IS4242", "Intelligent Systems and Techniques",
            "This course provides a broad coverage of intelligent systems in various industries (through examples of "
            + "real world applications) and the tools and techniques used to design such intelligent systems (e.g. "
            + "data warehousing, data mining and optimization). Applications from several domains such as finance, "
            + "healthcare, transportation, web and retail are discussed. The use of technology to solve business "
            + "problems (such as real-time optimization, personalization, trend discovery and unstructured data "
            + "analysis) are described. Software tools to apply these techniques are introduced. The emphasis of the "
            + "course is on modelling, conceptual understanding of techniques, and applications to business problems."),
        IS4243("IS4243", "Information Systems Consulting",
            "The aims and objectives are: (1) to provide an overview of Information Systems (IS) consulting and to "
            + "develop a more specific understanding of the practice; (2) to provide students with the knowledge of "
            + "management and IS consulting practices; and (3) to give students the opportunity to be involved in a "
            + "field consulting project."),
        IS4246("IS4246", "Smart Systems and AI Governance",
            "This course is designed for students who want to design and manage smart systems that use Artificial "
            + "Intelligence (AI) and Machine Learning (ML) to improve product and service offerings in enterprises "
            + "and businesses across different industries. It covers the use of an AI governance framework to ensure "
            + "the design and deployment of intelligent systems protect individual and organisational interests such "
            + "as transparency, safety, well-being, fairness, privacy and security. Application areas include AI/ML "
            + "in smart hospitals, autonomous vehicles, intelligent energy management with edge AI for business and "
            + "homes, and many more."),
        IS4250("IS4250", "IT-enabled Healthcare Solutioning",
            "This course is to provide students with hands-on, problem-based experience in Information Technology "
            + "(IT) design to tackle real-world healthcare challenges. Healthcare systems worldwide are in the midst "
            + "of changing their core strategies, financing and operational care processes. For instance, reactive "
            + "sick care is replaced by proactive efforts to keep people healthy and out of the hospital. Large-scale"
            + " healthcare systems are also being redesigned to promote continuity of care. In this course, students "
            + "can learn how to provide workable IT solutions to address contemporary healthcare issues and "
            + "challenges."),
        IS4261("IS4261", "Designing IT-enabled Business Innovations",
            "The course will teach students how to create and design innovation-driven business models through both "
            + "process innovation and product innovation. The focus is on businesses that are enabled and driven by "
            + "digital innovations. The course contents will cover disruptive technologies, cross-channel business "
            + "model development, mobilization of networked business, canvas drawing, social media-based product and "
            + "marketing innovation, etc. In particular, students will learn how to identify technology innovation "
            + "opportunities and manage innovation process , enabling them to appreciate the value of IT ecosystems "
            + "and platform-based business operations."),
        IS4262("IS4262", "Digital Product Management",
            "This course is designed for students who wants to learn the skills and techniques to manage effectively "
            + "digital products from cradle to grave. It covers the core aspects of digital product management, from "
            + "product strategy, planning and development to product launch and support. It will address issues of "
            + "managing an evolving digital product over its life cycle, and using data from customer insights and "
            + "competitive analysis for ongoing product iterations. Case studies and hands on experience will be "
            + "provided. At the end of the course, students will be able to effectively execute the product manager’s"
            + " role in managing digital products."),
        IS4301("IS4301", "Agile IT with DevOps",
            "Acceleration of the pace of digital transformation and adaption to business changes have caused IT "
            + "organizations to integrate Agile methods and DevOps with traditional IT development and operations. "
            + "This course introduces students with essential concepts of Agile IT and DevOps for participation in "
            + "agile IT business transformations. Topics covered shall include waterfall vs. agile, integrated agile "
            + "methods (Xtreme Programming, Scrum), DevOps, hybrid-IT, Platform as a service, monolithic vs. "
            + "microservice architecture, containerization, toolchains, open innovations and case studies. Banking "
            + "industry services will be used to enable students to practise concepts taught in this course."),
        IS4302("IS4302", "Blockchain and Distributed Ledger Technologies",
            "Digital currencies like Bitcoins have created a different, faster and potentially cheaper way of "
            + "monetary transfers. The technology behind Bitcoins, namely Blockchain or more broadly the distributed "
            + "ledger system, has brought big impact on financial services. A blockchain is a distributed database of"
            + " ownership records (public ledger) of all transactions. A blockchain is irrevocable once it is "
            + "committed into the system. Through this course, students will learn about blockchain and distributed "
            + "ledger technologies among others."),
        IS4303("IS4303", "IT-mediated financial solutions and platforms",
            "This course introduces students to new platforms in the financial industry to meet existing needs, or to"
            + " reach out to new markets. There will be an emphasis on platforms to help end consumers through "
            + "transactions, payment systems, and loans. Students will get an appreciation for innovation in FinTech,"
            + " and understand the changing landscape. They will also have practice using Big Data-based credit "
            + "scoring."),
        IT1001("IT1001", "Introduction to Computing",
            "This course aims to provide basic IT understanding for students who have no or little knowledge of "
            + "computing. It is structured to be the course for students who either plans to take only one course in "
            + "computing in her entire undergraduate studies or wants to equip herself to do further more specialised"
            + " computing studies. The course tries to be broad by touching on most aspects of computing. However, "
            + "there will also be some technical depth in standard introductory computing topics. The lectures will "
            + "be intensely complemented by Web exploring activities."),
        IT1244("IT1244", "Artificial Intelligence: Technology and Impact",
            "This course introduces students to artificial intelligence, which is becoming a general purpose "
            + "technology with impact in multiple areas in society, including in the sciences, arts, and business. "
            + "Topics covered include a conceptual understanding of how artificial intelligence works, current "
            + "strengths and weaknesses of artificial intelligence relative to humans, and the risks in deploying AIs"
            + ". Students are expected to implement a simple AI proof-of-concept, and to analyse its potential "
            + "benefits as well as its risks. Students taking this course are expected to have prior exposure to "
            + "programming and to be familiar with variables, types, operators, arrays, conditionals, loops, and "
            + "functions."),
        IT2002("IT2002", "Database Technology and Management",
            "The aim of this course is to provide students with practical knowledge and understanding of basic issues"
            + " and techniques in data management, with sufficient theory to understand the reasons for these "
            + "techniques. Topics include conceptual (entity relationship model) and logical design (relational "
            + "model) of database models, relational database management (data definition, data manipulation, SQL, "
            + "visual interactive query interfaces), and their use in application development (in particular, data "
            + "extraction from DBMS to spreadsheets application and data extraction to Web applications). Projects in"
            + " developing a database within an application form an essential component of this course."),
        IT3010("IT3010", "Data Management for Business Analytics",
            "This course is highly applied in nature with two important database topics, namely, traditional "
            + "relational databases and SQL as well as non-traditional databases and NoSQL queries, to students "
            + "outside School of Computing. Students are expected to know basic programming using Python."),
        IT3011("IT3011", "Introduction to Machine Learning and Applications",
            "This course provides students with a broad understanding of the concepts, limitations, and applications "
            + "of machine learning. Students will be exposed to the fundamental concepts of machine learning, "
            + "including supervised learning (regression and classification), clustering, and methods such as SVM, "
            + "neural networks, decision trees, and ensemble methods. Pitfalls and limitations such as overfitting, "
            + "data leakage, bias, and ethical issues will be covered. This course also emphasizes on the "
            + "applications of machine learning to problems in different domains (such as medicine, finance, "
            + "engineering, science, business, social science) and is suitable for non computing students.");

        private final String code;
        private final String name;
        private final String description;

        SampleLocalCourse(String code, String name, String description) {
            this.code = code;
            this.name = name;
            this.description = description;
        }

        /**
         * Gets the string representation of the course code.
         *
         * @return The string representation of the course code.
         */
        public String getCode() {
            return this.code;
        }

        /**
         * Gets the string representation of the course name.
         *
         * @return The string representation of the course name.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Gets the string representation of the course name.
         *
         * @return The string representation of the course description.
         */
        public String getDescription() {
            return this.description;
        }
    }

    /**
     * Returns an array of sample local courses.
     *
     * @return An array of sample {@code LocalCourse} objects.
     */
    public static LocalCourse[] getSampleLocalCourses() {
        return Arrays.stream(SampleLocalCourse.values())
                     .map(data -> new LocalCourse(
                         new LocalCode(data.getCode()),
                         new LocalName(data.getName()),
                         new LocalUnit(4.0),
                         new LocalDescription(data.getDescription()
                         )))
                     .toArray(LocalCourse[]::new);
    }
}
