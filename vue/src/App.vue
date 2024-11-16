<template>
  <cv-header aria-label="Smash application header">
    <cv-skip-to-content href="#main-content">
      Skip to content
    </cv-skip-to-content>
    <cv-header-name href="javascript:void(0)" prefix="SMASH">
      Test App
    </cv-header-name>
    <cv-header-nav aria-label="Carbon nav">
      <cv-header-menu-item href="/">
        Home
      </cv-header-menu-item>
      <cv-header-menu-item href="/customers">
        Customers
      </cv-header-menu-item>      
    </cv-header-nav>
  </cv-header>
  <cv-grid :full-width="true" kind="wide">
    <cv-row>
      <cv-column :sm="4" :md="8" :lg="12">
        <RouterView />
      </cv-column>
    </cv-row>
  </cv-grid>
</template>

<style scoped></style>
