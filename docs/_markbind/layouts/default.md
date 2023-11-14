<head-bottom>
  <link rel="stylesheet" href="{{baseUrl}}/stylesheets/main.css">
</head-bottom>

<header sticky>
  <navbar type="dark">
    <a slot="brand" href="{{baseUrl}}/index.html" title="Home" class="navbar-brand">FreelanceBuddy</a>
    <li><a href="{{baseUrl}}/UserGuide.html" class="nav-link">User Guide</a></li>
    <li><a href="{{baseUrl}}/DeveloperGuide.html" class="nav-link">Developer Guide</a></li>
    <li><a href="{{baseUrl}}/AboutUs.html" class="nav-link">About Us</a></li>
    <li><a href="https://github.com/AY2324S1-CS2103T-W09-2/tp" target="_blank" class="nav-link"><md>:fab-github:</md></a>
    </li>
    <li slot="right">
      <form class="navbar-form">
        <searchbar :data="searchData" placeholder="Search" :on-hit="searchCallback" menu-align-right></searchbar>
      </form>
    </li>
  </navbar>
</header>

<div id="flex-body">
  <nav id="site-nav">
    <div class="site-nav-top">
      <div class="fw-bold mb-2" style="font-size: 1.25rem;">Site Map</div>
    </div>
    <div class="nav-component slim-scroll">
      <site-nav>
* [Home]({{ baseUrl }}/index.html)
* [User Guide]({{ baseUrl }}/UserGuide.html) :expanded:
  * [Getting Started]({{ baseUrl }}/UserGuide.html#getting-started)
    * [Quick Start <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#quick-start)
    * [Navigating this user guide <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#quick-start)
    * [Features <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#features)
  * [Commands]({{ baseUrl }}/UserGuide.html#commands)
    * [General Commands <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#general-commands)
    * [Contacts Management <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#contacts-management)
    * [Finance Management <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#finance-management)
    * [Events Management <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#events-management)
  * [Data Storage]({{ baseUrl }}/UserGuide.html#data-storage)
    * [Saving the data <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#saving-the-data)
    * [Editing the data file <span class="badge rounded-pill bg-danger" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#editing-the-data-file)
    * [Configuration Files <span class="badge rounded-pill bg-danger" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#configuration-files)
  * [Accepted Date-time Formats <span class="badge rounded-pill bg-success" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#accepted-date-time-formats)
  * [Command Summary <span class="badge rounded-pill bg-danger" data-v-4d146e2c=""><span aria-hidden="true" class="far fa-star" data-v-4d146e2c=""></span></span>]({{ baseUrl }}/UserGuide.html#command-summary)
    * [General]({{ baseUrl }}/UserGuide.html#general)
    * [Contacts Tab]({{ baseUrl }}/UserGuide.html#contacts-tab)
    * [Finance Tab]({{ baseUrl }}/UserGuide.html#finance-tab)
    * [Events Tab]({{ baseUrl }}/UserGuide.html#events-tab)
  * [FAQ]({{ baseUrl }}/UserGuide.html#faq)
* [Developer Guide]({{ baseUrl }}/DeveloperGuide.html) :expanded:
  * [Acknowledgements]({{ baseUrl }}/DeveloperGuide.html#acknowledgements)
  * [Setting Up]({{ baseUrl }}/DeveloperGuide.html#setting-up-getting-started)
  * [Design]({{ baseUrl }}/DeveloperGuide.html#design)
  * [Implementation]({{ baseUrl }}/DeveloperGuide.html#implementation)
  * [Documentation, logging, testing, configuration, dev-ops]({{ baseUrl }}/DeveloperGuide.html#documentation-logging-testing-configuration-dev-ops)
  * [Appendix: Requirements]({{ baseUrl }}/DeveloperGuide.html#appendix-requirements)
  * [Appendix: Instructions for manual testing]({{ baseUrl }}/DeveloperGuide.html#appendix-instructions-for-manual-testing)
* Tutorials
  * [Tracing code]({{ baseUrl }}/tutorials/TracingCode.html)
  * [Adding a command]({{ baseUrl }}/tutorials/AddRemark.html)
  * [Removing Fields]({{ baseUrl }}/tutorials/RemovingFields.html)
* [About Us]({{ baseUrl }}/AboutUs.html)
      </site-nav>
    </div>
  </nav>
  <div id="content-wrapper">
    {{ content }}
  </div>
  <nav id="page-nav">
    <div class="nav-component slim-scroll">
      <page-nav />
    </div>
  </nav>
<scroll-top-button
    icon=":fas-arrow-circle-up:"
    icon-size="2x"
    bottom="2%"
    right="2%"
></scroll-top-button>
</div>

<footer>
  <!-- Support MarkBind by including a link to us on your landing page! -->
  <div class="text-center">
    <small>[<md>**Powered by**</md> <img src="https://markbind.org/favicon.ico" width="30"> {{MarkBind}}, generated on {{timestamp}}]</small>
  </div>
</footer>


