<head-bottom>
  <link rel="stylesheet" href="{{baseUrl}}/stylesheets/main.css">
</head-bottom>

<header sticky>
  <navbar type="dark">
    <a slot="brand" href="{{baseUrl}}/index.html" title="Home" class="navbar-brand">WedLog</a>
    <li><a href="{{baseUrl}}/UserGuide.html" class="nav-link">User Guide</a></li>
    <li><a href="{{baseUrl}}/DeveloperGuide.html" class="nav-link">Developer Guide</a></li>
    <li><a href="{{baseUrl}}/AboutUs.html" class="nav-link">About Us</a></li>
    <li><a href="https://github.com/AY2324S1-CS2103T-F11-2/tp/" target="_blank" class="nav-link"><md>:fab-github:</md></a>
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
  * [How to Use This Guide]({{ baseUrl }}/UserGuide.html#1-how-to-use-this-guide)
  * [Getting Started]({{ baseUrl }}/UserGuide.html#2-getting-started)
  * [Understanding the WedLog Interface]({{ baseUrl }}/UserGuide.html#3-understanding-the-wedlog-interface)
  * [WedLog Tutorial]({{ baseUrl }}/UserGuide.html#4-wedlog-tutorial)
  * [Features]({{ baseUrl }}/UserGuide.html#5-features)
  * [FAQ]({{ baseUrl }}/UserGuide.html#6-faq)
  * [Known Issues]({{ baseUrl }}/UserGuide.html#7-known-issues)
  * [Future Implementations]({{ baseUrl }}/UserGuide.html#8-future-implementations)
  * [Command Summary]({{ baseUrl }}/UserGuide.html#9-command-summary)
  * [Appendix: Acceptable Values for Parameters]({{ baseUrl }}/UserGuide.html#10-appendix-acceptable-values-for-parameters)
* [Developer Guide]({{ baseUrl }}/DeveloperGuide.html) :expanded:
  * [Acknowledgements]({{ baseUrl }}/DeveloperGuide.html#1-acknowledgements)
  * [Setting Up, Getting Started]({{ baseUrl }}/DeveloperGuide.html#2-setting-up-getting-started)
  * [Design]({{ baseUrl }}/DeveloperGuide.html#3-design)
  * [Implementation]({{ baseUrl }}/DeveloperGuide.html#4-implementation)
  * [Documentation, logging, testing, configuration, dev-ops]({{ baseUrl }}/DeveloperGuide.html#5-documentation-logging-testing-configuration-dev-ops)
  * [Appendix A: Requirements]({{ baseUrl }}/DeveloperGuide.html#6-appendix-a-requirements)
  * [Appendix B: Instructions for manual testing]({{ baseUrl }}/DeveloperGuide.html#7-appendix-b-instructions-for-manual-testing)
  * [Appendix C: Planned Enhancements]({{ baseUrl }}/DeveloperGuide.html#8-appendix-c-planned-enhancements)
  * [Appendix D: Effort]({{baseUrl}}/DeveloperGuide.html#9-appendix-d-effort)
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
  <scroll-top-button></scroll-top-button>
</div>

<footer>
  <!-- Support MarkBind by including a link to us on your landing page! -->
  <div class="text-center">
    <small>[<md>**Powered by**</md> <img src="https://markbind.org/favicon.ico" width="30"> {{MarkBind}}, generated on {{timestamp}}]</small>
  </div>
</footer>
