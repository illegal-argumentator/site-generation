package com.elias.site_generation.adapter.theme.out.prompt;

public final class RegentCasinoStyleSamples {

    public static final String REGENT_CLUB_HOME_PAGE_STYLES = """
            /* =========================================================
               1. DESIGN TOKENS — edit these to re-skin the whole site
               ========================================================= */
            :root{
              /* --- colors --- */
              --paper:      #EEF3F7;
              --card:       #FFFFFF;
              --ink:        #14213D;
              --ink-soft:   #4A5875;
              --red:        #E63A2E;
              --red-dark:   #B8261D;
              --lemon:      #FFD447;
              --pool:       #22A6B3;
              --mint:       #A6E8D3;

              /* --- typography --- */
              --font-display: 'Bricolage Grotesque', 'Arial Narrow', Arial, sans-serif;
              --font-body:    'Figtree', 'Segoe UI', Arial, sans-serif;

              /* --- shape / depth --- */
              --line:       2px solid var(--ink);
              --shadow:     5px 5px 0 var(--ink);
              --shadow-sm:  3px 3px 0 var(--ink);
              --r-sm:       10px;
              --r:          18px;
              --r-lg:       28px;
              --ease:       cubic-bezier(.2,.9,.3,1);

              --container:  1200px;
            }

            @media (prefers-reduced-motion: reduce){
              *,*::before,*::after{ animation-duration:.001ms !important; animation-iteration-count:1 !important; transition-duration:.001ms !important; }
            }

            /* =========================================================
               2. RESET & BASE
               ========================================================= */
            *,*::before,*::after{ box-sizing:border-box; }
            html{ scroll-behavior:smooth; }
            body{
              margin:0;
              background:var(--paper);
              color:var(--ink);
              font-family:var(--font-body);
              font-size:17px;
              line-height:1.6;
              -webkit-font-smoothing:antialiased;
            }
            img{ max-width:100%; display:block; }
            a{ color:inherit; }
            ul{ margin:0; padding:0; list-style:none; }
            h1,h2,h3,h4{ margin:0; font-family:var(--font-display); font-weight:800; line-height:1.02; letter-spacing:-.02em; font-stretch:80%; font-variation-settings:"wdth" 80; }
            p{ margin:0; }
            button,input{ font:inherit; }
            button{ cursor:pointer; }
            :focus-visible{ outline:3px solid var(--pool); outline-offset:3px; }
            [hidden]{ display:none !important; }

            /* anchors land below the 76px sticky header */
            section[id], .faq-group{ scroll-margin-top:92px; }

            .wrap{ width:100%; max-width:var(--container); margin:0 auto; padding:0 24px; }
            .block{ padding:110px 0; }

            .sec-title{ font-size:clamp(40px,6vw,76px); max-width:14ch; }
            .sec-lead{ margin-top:18px; max-width:52ch; color:var(--ink-soft); font-size:18px; }

            /* buttons */
            .pill{
              display:inline-flex; align-items:center; justify-content:center; gap:8px;
              padding:14px 28px; border:var(--line); border-radius:999px;
              font-weight:700; font-size:16px; text-decoration:none; white-space:nowrap;
              background:var(--card); color:var(--ink); box-shadow:var(--shadow-sm);
              transition:transform .15s var(--ease), box-shadow .15s var(--ease), background .15s;
            }
            .pill:hover{ transform:translate(-2px,-2px); box-shadow:5px 5px 0 var(--ink); }
            .pill:active{ transform:translate(3px,3px); box-shadow:0 0 0 var(--ink); }
            .pill-red{ background:var(--red); color:#fff; }
            .pill-lemon{ background:var(--lemon); }
            .pill-sm{ padding:9px 18px; font-size:14px; }
            .pill-wide{ width:100%; }

            /* mini casino chip used in logo + decorations */
            .chip-mini{
              width:38px; height:38px; border-radius:50%; flex:none;
              background:repeating-conic-gradient(var(--red) 0 22.5deg, #fff 22.5deg 45deg);
              border:var(--line); position:relative;
            }
            .chip-mini::after{
              content:""; position:absolute; inset:6px; border-radius:50%;
              background:var(--red); border:2px solid #fff;
            }

            /* =========================================================
               3. HEADER
               ========================================================= */
            #site-header{
              position:sticky; top:0; z-index:100;
              background:var(--card);
              border-bottom:var(--line);
            }
            .bar{ display:flex; align-items:center; justify-content:space-between; gap:20px; height:76px; }
            .brand{ display:flex; align-items:center; gap:10px; text-decoration:none; font-family:var(--font-display); font-weight:800; font-size:28px; letter-spacing:-.03em; }
            .brand small{ font-family:var(--font-body); font-size:13px; font-weight:600; color:var(--ink-soft); letter-spacing:0; margin-left:2px; align-self:flex-end; padding-bottom:6px; }

            .menu{ display:flex; gap:6px; }
            .menu a{
              text-decoration:none; font-weight:600; font-size:15px;
              padding:8px 16px; border-radius:999px; border:2px solid transparent;
            }
            .menu a:hover{ border-color:var(--ink); }
            .menu a[aria-current="page"]{ background:var(--lemon); border-color:var(--ink); }

            .bar-actions{ display:flex; align-items:center; gap:10px; }
            .burger{
              display:none; width:46px; height:46px; border:var(--line); border-radius:50%;
              background:var(--card); align-items:center; justify-content:center; padding:0;
            }
            .burger span, .burger span::before, .burger span::after{
              display:block; width:18px; height:2px; background:var(--ink); position:relative; content:""; transition:transform .2s;
            }
            .burger span::before{ position:absolute; top:-6px; }
            .burger span::after{ position:absolute; top:6px; }
            .burger[aria-expanded="true"] span{ background:transparent; }
            .burger[aria-expanded="true"] span::before{ transform:translateY(6px) rotate(45deg); }
            .burger[aria-expanded="true"] span::after{ transform:translateY(-6px) rotate(-45deg); }

            @media (max-width:900px){
              .burger{ display:inline-flex; }
              .bar-actions .pill:not(.pill-red){ display:none; }
              .menu{
                position:absolute; left:0; right:0; top:100%;
                flex-direction:column; gap:4px; padding:16px 24px 24px;
                background:var(--card); border-bottom:var(--line);
                display:none;
              }
              .menu.is-open{ display:flex; }
              .menu a{ padding:12px 16px; font-size:17px; }
            }
            @media (max-width:420px){ .bar-actions .pill-red{ display:none; } }

            /* =========================================================
               4. HERO + signature slot machine
               ========================================================= */
            #hero{
              background:var(--lemon);
              border-bottom:var(--line);
              padding:72px 0 88px;
              overflow:hidden;
            }
            .hero-grid{ display:grid; grid-template-columns:1.1fr .9fr; gap:56px; align-items:center; }
            #hero h1{ font-size:clamp(52px,8.4vw,112px); max-width:9ch; }
            .hero-text{ margin-top:24px; max-width:44ch; font-size:19px; }
            .hero-cta{ display:flex; flex-wrap:wrap; gap:14px; margin-top:34px; }
            .hero-note{ margin-top:22px; font-size:14px; color:var(--ink-soft); }

            .slot{
              justify-self:center; width:100%; max-width:430px;
              background:var(--red); border:var(--line); border-radius:var(--r-lg) var(--r-lg) var(--r) var(--r);
              box-shadow:10px 10px 0 var(--ink); padding:22px 22px 26px; position:relative;
            }
            .slot-top{
              display:flex; justify-content:space-between; align-items:center;
              color:#fff; font-family:var(--font-display); font-weight:800; font-size:26px; margin-bottom:16px;
            }
            .slot-lights{ display:flex; gap:6px; }
            .slot-lights i{ width:12px; height:12px; border-radius:50%; background:var(--lemon); border:2px solid var(--ink); }
            .slot-window{
              display:grid; grid-template-columns:repeat(3,1fr); gap:10px;
              background:var(--ink); border-radius:var(--r); padding:10px;
            }
            .reel{ height:120px; overflow:hidden; background:var(--card); border-radius:var(--r-sm); position:relative; }
            .reel::before,.reel::after{ content:""; position:absolute; left:0; right:0; height:22px; z-index:2; pointer-events:none; }
            .reel::before{ top:0; background:linear-gradient(rgba(20,33,61,.28),transparent); }
            .reel::after{ bottom:0; background:linear-gradient(transparent,rgba(20,33,61,.28)); }
            .reel-strip{ will-change:transform; }
            .reel-strip span{
              height:120px; display:flex; align-items:center; justify-content:center;
              font-family:var(--font-display); font-weight:800; font-size:58px; line-height:1;
            }
            .reel-strip .s-7{ color:var(--red); }
            .reel-strip .s-star{ color:#E0A800; }
            .reel-strip .s-dia{ color:var(--pool); }
            .reel-strip .s-bar{ font-size:34px; color:var(--ink); }
            .reel-strip .s-club{ color:#1E8A5A; }
            .slot-result{
              margin-top:16px; min-height:28px; text-align:center;
              color:#fff; font-weight:700; font-size:16px;
            }
            .slot-spin{ margin-top:14px; }
            .slot-foot{ margin-top:12px; color:#FFD9D5; font-size:13px; text-align:center; }

            @media (max-width:920px){
              .hero-grid{ grid-template-columns:1fr; }
              .slot{ justify-self:start; }
            }

            /* =========================================================
               5. STATS — casino chips
               ========================================================= */
            #stats{ padding:80px 0; border-bottom:var(--line); background:var(--card); }
            .chips{ display:grid; grid-template-columns:repeat(4,1fr); gap:28px; }
            .stat-chip{ display:flex; flex-direction:column; align-items:center; text-align:center; gap:14px; }
            .stat-ring{
              --c:var(--red);
              width:168px; height:168px; border-radius:50%; border:var(--line);
              background:repeating-conic-gradient(var(--c) 0 15deg, var(--card) 15deg 30deg);
              display:grid; place-items:center; box-shadow:var(--shadow);
            }
            .stat-ring b{
              width:122px; height:122px; border-radius:50%; background:var(--c); border:3px dashed rgba(255,255,255,.85);
              display:grid; place-items:center; color:#fff;
              font-family:var(--font-display); font-weight:800; font-size:34px; letter-spacing:-.03em;
            }
            .stat-chip:nth-child(2) .stat-ring{ --c:var(--pool); }
            .stat-chip:nth-child(3) .stat-ring{ --c:var(--ink); }
            .stat-chip:nth-child(4) .stat-ring{ --c:#E0A800; }
            .stat-chip p{ font-weight:600; max-width:18ch; }
            @media (max-width:860px){ .chips{ grid-template-columns:repeat(2,1fr); row-gap:44px; } }
            @media (max-width:420px){ .stat-ring{ width:140px; height:140px; } .stat-ring b{ width:100px; height:100px; font-size:28px; } }

            /* =========================================================
               6. FEATURES — the paytable
               ========================================================= */
            #features .wrap{ display:grid; grid-template-columns:.8fr 1.2fr; gap:64px; align-items:start; }
            .paytable{ background:var(--card); border:var(--line); border-radius:var(--r); box-shadow:var(--shadow); overflow:hidden; }
            .pay-row{ display:grid; grid-template-columns:auto 1fr; gap:24px; align-items:center; padding:26px 28px; }
            .pay-row + .pay-row{ border-top:2px dashed rgba(20,33,61,.35); }
            .combo{ display:flex; gap:5px; }
            .combo span{
              width:40px; height:48px; border:var(--line); border-radius:8px; background:var(--paper);
              display:grid; place-items:center; font-family:var(--font-display); font-weight:800; font-size:24px;
            }
            .pay-row h3{ font-size:28px; }
            .pay-row p{ margin-top:6px; color:var(--ink-soft); font-size:16px; }
            @media (max-width:900px){ #features .wrap{ grid-template-columns:1fr; gap:40px; } }
            @media (max-width:560px){ .pay-row{ grid-template-columns:1fr; gap:14px; } }

            /* =========================================================
               7. GAMES — lobby
               ========================================================= */
            #games{ background:var(--mint); border-top:var(--line); border-bottom:var(--line); }
            .games-head{ display:flex; justify-content:space-between; align-items:flex-end; gap:24px; flex-wrap:wrap; margin-bottom:48px; }
            .lobby{ display:grid; grid-template-columns:repeat(4,1fr); gap:24px; }
            .game{
              background:var(--card); border:var(--line); border-radius:var(--r); box-shadow:var(--shadow);
              overflow:hidden; display:flex; flex-direction:column;
            }
            .game-art{ position:relative; aspect-ratio:4/3; background:var(--paper); border-bottom:var(--line); }
            .game-art .badge{
              position:absolute; top:12px; left:12px; z-index:2;
              padding:4px 12px; border:var(--line); border-radius:999px; background:var(--lemon);
              font-size:13px; font-weight:700;
            }
            .game-info{ padding:18px 18px 20px; display:flex; flex-direction:column; gap:10px; flex:1; }
            .game-info h3{ font-size:26px; }
            .game-meta{ display:flex; gap:14px; font-size:14px; color:var(--ink-soft); font-weight:600; }
            .game-info .pill{ margin-top:auto; }
            .game--big{ grid-column:span 2; grid-row:span 2; }
            .game--big .game-art{ aspect-ratio:auto; flex:1; min-height:260px; }
            .game--big .game-info{ flex:none; }
            .game--big h3{ font-size:40px; }
            @media (max-width:1000px){ .lobby{ grid-template-columns:repeat(2,1fr); } .game--big{ grid-row:auto; } }
            @media (max-width:560px){ .lobby{ grid-template-columns:1fr; } .game--big{ grid-column:auto; grid-row:auto; } }

            /* =========================================================
               8. JACKPOT — odometer counter
               ========================================================= */
            #jackpot{ background:var(--ink); color:#fff; text-align:center; }
            #jackpot .sec-title{ margin:0 auto; max-width:20ch; color:#fff; }
            #jackpot .sec-lead{ margin:18px auto 0; color:#C5D0E4; }
            .odometer{
              display:inline-flex; gap:6px; margin-top:48px; padding:16px;
              background:var(--red); border:2px solid #fff; border-radius:var(--r); box-shadow:8px 8px 0 var(--lemon);
              max-width:100%; overflow-x:auto;
            }
            .odometer span{
              min-width:62px; height:92px; padding:0 4px; border-radius:var(--r-sm);
              background:var(--card); color:var(--ink);
              display:grid; place-items:center;
              font-family:var(--font-display); font-weight:800; font-size:64px; font-variant-numeric:tabular-nums;
            }
            .odometer .sep{ min-width:auto; background:none; color:#fff; padding:0 2px; }
            .jackpot-last{ margin-top:28px; color:#C5D0E4; font-size:15px; }
            .jackpot-last b{ color:var(--lemon); }
            @media (max-width:640px){
              .odometer{ gap:3px; padding:10px; }
              .odometer span{ min-width:30px; height:54px; font-size:34px; }
            }

            /* =========================================================
               9. FAQ (home preview + full page items)
               ========================================================= */
            #faq .wrap{ display:grid; grid-template-columns:.8fr 1.2fr; gap:64px; align-items:start; }
            .faq-side{ position:sticky; top:110px; }
            .faq-side .pill{ margin-top:28px; }
            .qa-list{ display:flex; flex-direction:column; gap:14px; }
            .qa{ background:var(--card); border:var(--line); border-radius:var(--r); transition:box-shadow .15s; }
            .qa[open]{ box-shadow:var(--shadow); }
            .qa summary{
              list-style:none; cursor:pointer; display:flex; justify-content:space-between; align-items:center; gap:20px;
              padding:20px 24px; font-weight:700; font-size:18px;
            }
            .qa summary::-webkit-details-marker{ display:none; }
            .qa summary::after{
              content:"+"; flex:none; width:32px; height:32px; border:var(--line); border-radius:50%;
              display:grid; place-items:center; font-size:20px; line-height:1; transition:transform .2s var(--ease), background .2s;
            }
            .qa[open] summary::after{ transform:rotate(45deg); background:var(--lemon); }
            .qa p{ padding:0 24px 22px; color:var(--ink-soft); max-width:62ch; }
            @media (max-width:900px){ #faq .wrap{ grid-template-columns:1fr; gap:36px; } .faq-side{ position:static; } }

            /* =========================================================
               10. CTA — the ticket
               ========================================================= */
            #cta{ padding:0 0 110px; }
            .ticket{
              position:relative; display:grid; grid-template-columns:1.2fr 1fr; align-items:center;
              background:var(--red); color:#fff; border:var(--line); border-radius:var(--r-lg); box-shadow:10px 10px 0 var(--ink);
            }
            .ticket::before,.ticket::after{
              content:""; position:absolute; top:50%; width:44px; height:44px; margin-top:-22px;
              border-radius:50%; background:var(--paper); border:var(--line);
            }
            .ticket::before{ left:-24px; clip-path:inset(0 0 0 50%); }
            .ticket::after{ right:-24px; clip-path:inset(0 50% 0 0); }
            .ticket-main{ padding:56px 48px; border-right:3px dashed rgba(255,255,255,.6); }
            .ticket-main h2{ font-size:clamp(38px,5vw,64px); max-width:12ch; }
            .ticket-main p{ margin-top:16px; max-width:42ch; color:#FFE3E0; }
            .ticket-form{ padding:48px; display:flex; flex-direction:column; gap:12px; }
            .ticket-form label{ font-weight:700; font-size:15px; }
            .ticket-form input{
              width:100%; padding:15px 20px; border:var(--line); border-radius:999px; background:#fff; color:var(--ink);
            }
            .ticket-form input:focus{ outline:3px solid var(--lemon); outline-offset:2px; }
            .ticket-form small{ color:#FFE3E0; font-size:13px; }
            @media (max-width:860px){
              .ticket{ grid-template-columns:1fr; }
              .ticket::before,.ticket::after{ display:none; }
              .ticket-main{ border-right:0; border-bottom:3px dashed rgba(255,255,255,.6); padding:40px 28px; }
              .ticket-form{ padding:32px 28px 40px; }
            }

            /* =========================================================
               11. FOOTER
               ========================================================= */
            #site-footer{ background:var(--ink); color:#C5D0E4; padding:80px 0 32px; border-top:var(--line); }
            .foot-grid{ display:grid; grid-template-columns:1.5fr 1fr 1fr 1.3fr; gap:40px; }
            #site-footer .brand{ color:#fff; font-size:28px; text-decoration:none; }
            #site-footer .brand small{ color:#C5D0E4; }
            .foot-about{ margin-top:16px; max-width:34ch; font-size:15px; }
            .foot-grid h4{ color:#fff; font-size:22px; margin-bottom:14px; }
            .foot-grid ul{ display:flex; flex-direction:column; gap:8px; }
            .foot-grid a{ text-decoration:none; font-size:15px; }
            .foot-grid a:hover{ color:var(--lemon); text-decoration:underline; }
            .socials{ display:flex; gap:10px; margin-top:22px; }
            .socials a{
              width:42px; height:42px; border-radius:50%; border:2px solid #C5D0E4;
              display:grid; place-items:center; color:#fff;
            }
            .socials a:hover{ background:var(--lemon); color:var(--ink); border-color:var(--lemon); }
            .pay-tags{ display:flex; flex-wrap:wrap; gap:8px; }
            .pay-tags span{ padding:6px 12px; border-radius:999px; background:rgba(255,255,255,.08); border:1px solid rgba(255,255,255,.2); font-size:13px; font-weight:600; color:#fff; }
            .foot-bottom{
              margin-top:60px; padding-top:24px; border-top:1px solid rgba(255,255,255,.18);
              display:flex; justify-content:space-between; align-items:center; gap:20px; flex-wrap:wrap; font-size:13px;
            }
            .age{
              display:inline-grid; place-items:center; width:34px; height:34px; margin-right:10px; vertical-align:middle;
              border-radius:50%; background:var(--red); color:#fff; font-weight:800; font-size:13px; border:2px solid #fff;
            }
            @media (max-width:900px){ .foot-grid{ grid-template-columns:repeat(2,1fr); } }
            @media (max-width:520px){ .foot-grid{ grid-template-columns:1fr; } }
            """;

    public static final String REGENT_CLUB_COOKIES_PAGE_STYLES = """
            /* =========================================================
               1. DESIGN TOKENS — edit these to re-skin the whole site
               ========================================================= */
            :root{
              /* --- colors --- */
              --paper:      #EEF3F7;
              --card:       #FFFFFF;
              --ink:        #14213D;
              --ink-soft:   #4A5875;
              --red:        #E63A2E;
              --red-dark:   #B8261D;
              --lemon:      #FFD447;
              --pool:       #22A6B3;
              --mint:       #A6E8D3;

              /* --- typography --- */
              --font-display: 'Bricolage Grotesque', 'Arial Narrow', Arial, sans-serif;
              --font-body:    'Figtree', 'Segoe UI', Arial, sans-serif;

              /* --- shape / depth --- */
              --line:       2px solid var(--ink);
              --shadow:     5px 5px 0 var(--ink);
              --shadow-sm:  3px 3px 0 var(--ink);
              --r-sm:       10px;
              --r:          18px;
              --r-lg:       28px;
              --ease:       cubic-bezier(.2,.9,.3,1);

              --container:  1200px;
            }

            @media (prefers-reduced-motion: reduce){
              *,*::before,*::after{ animation-duration:.001ms !important; animation-iteration-count:1 !important; transition-duration:.001ms !important; }
            }

            /* =========================================================
               2. RESET & BASE
               ========================================================= */
            *,*::before,*::after{ box-sizing:border-box; }
            html{ scroll-behavior:smooth; }
            body{
              margin:0;
              background:var(--paper);
              color:var(--ink);
              font-family:var(--font-body);
              font-size:17px;
              line-height:1.6;
              -webkit-font-smoothing:antialiased;
            }
            img{ max-width:100%; display:block; }
            a{ color:inherit; }
            ul{ margin:0; padding:0; list-style:none; }
            h1,h2,h3,h4{ margin:0; font-family:var(--font-display); font-weight:800; line-height:1.02; letter-spacing:-.02em; font-stretch:80%; font-variation-settings:"wdth" 80; }
            p{ margin:0; }
            button,input{ font:inherit; }
            button{ cursor:pointer; }
            :focus-visible{ outline:3px solid var(--pool); outline-offset:3px; }
            [hidden]{ display:none !important; }

            /* anchors land below the 76px sticky header */
            section[id], .faq-group{ scroll-margin-top:92px; }

            .wrap{ width:100%; max-width:var(--container); margin:0 auto; padding:0 24px; }
            .block{ padding:110px 0; }

            .sec-title{ font-size:clamp(40px,6vw,76px); max-width:14ch; }
            .sec-lead{ margin-top:18px; max-width:52ch; color:var(--ink-soft); font-size:18px; }

            /* buttons */
            .pill{
              display:inline-flex; align-items:center; justify-content:center; gap:8px;
              padding:14px 28px; border:var(--line); border-radius:999px;
              font-weight:700; font-size:16px; text-decoration:none; white-space:nowrap;
              background:var(--card); color:var(--ink); box-shadow:var(--shadow-sm);
              transition:transform .15s var(--ease), box-shadow .15s var(--ease), background .15s;
            }
            .pill:hover{ transform:translate(-2px,-2px); box-shadow:5px 5px 0 var(--ink); }
            .pill:active{ transform:translate(3px,3px); box-shadow:0 0 0 var(--ink); }
            .pill-red{ background:var(--red); color:#fff; }
            .pill-lemon{ background:var(--lemon); }
            .pill-sm{ padding:9px 18px; font-size:14px; }
            .pill-wide{ width:100%; }

            /* mini casino chip used in logo + decorations */
            .chip-mini{
              width:38px; height:38px; border-radius:50%; flex:none;
              background:repeating-conic-gradient(var(--red) 0 22.5deg, #fff 22.5deg 45deg);
              border:var(--line); position:relative;
            }
            .chip-mini::after{
              content:""; position:absolute; inset:6px; border-radius:50%;
              background:var(--red); border:2px solid #fff;
            }

            /* =========================================================
               3. HEADER
               ========================================================= */
            #site-header{
              position:sticky; top:0; z-index:100;
              background:var(--card);
              border-bottom:var(--line);
            }
            .bar{ display:flex; align-items:center; justify-content:space-between; gap:20px; height:76px; }
            .brand{ display:flex; align-items:center; gap:10px; text-decoration:none; font-family:var(--font-display); font-weight:800; font-size:28px; letter-spacing:-.03em; }
            .brand small{ font-family:var(--font-body); font-size:13px; font-weight:600; color:var(--ink-soft); letter-spacing:0; margin-left:2px; align-self:flex-end; padding-bottom:6px; }

            .menu{ display:flex; gap:6px; }
            .menu a{
              text-decoration:none; font-weight:600; font-size:15px;
              padding:8px 16px; border-radius:999px; border:2px solid transparent;
            }
            .menu a:hover{ border-color:var(--ink); }
            .menu a[aria-current="page"]{ background:var(--lemon); border-color:var(--ink); }

            .bar-actions{ display:flex; align-items:center; gap:10px; }
            .burger{
              display:none; width:46px; height:46px; border:var(--line); border-radius:50%;
              background:var(--card); align-items:center; justify-content:center; padding:0;
            }
            .burger span, .burger span::before, .burger span::after{
              display:block; width:18px; height:2px; background:var(--ink); position:relative; content:""; transition:transform .2s;
            }
            .burger span::before{ position:absolute; top:-6px; }
            .burger span::after{ position:absolute; top:6px; }
            .burger[aria-expanded="true"] span{ background:transparent; }
            .burger[aria-expanded="true"] span::before{ transform:translateY(6px) rotate(45deg); }
            .burger[aria-expanded="true"] span::after{ transform:translateY(-6px) rotate(-45deg); }

            @media (max-width:900px){
              .burger{ display:inline-flex; }
              .bar-actions .pill:not(.pill-red){ display:none; }
              .menu{
                position:absolute; left:0; right:0; top:100%;
                flex-direction:column; gap:4px; padding:16px 24px 24px;
                background:var(--card); border-bottom:var(--line);
                display:none;
              }
              .menu.is-open{ display:flex; }
              .menu a{ padding:12px 16px; font-size:17px; }
            }
            @media (max-width:420px){ .bar-actions .pill-red{ display:none; } }

            /* =========================================================
               11. FOOTER
               ========================================================= */
            #site-footer{ background:var(--ink); color:#C5D0E4; padding:80px 0 32px; border-top:var(--line); }
            .foot-grid{ display:grid; grid-template-columns:1.5fr 1fr 1fr 1.3fr; gap:40px; }
            #site-footer .brand{ color:#fff; font-size:28px; text-decoration:none; }
            #site-footer .brand small{ color:#C5D0E4; }
            .foot-about{ margin-top:16px; max-width:34ch; font-size:15px; }
            .foot-grid h4{ color:#fff; font-size:22px; margin-bottom:14px; }
            .foot-grid ul{ display:flex; flex-direction:column; gap:8px; }
            .foot-grid a{ text-decoration:none; font-size:15px; }
            .foot-grid a:hover{ color:var(--lemon); text-decoration:underline; }
            .socials{ display:flex; gap:10px; margin-top:22px; }
            .socials a{
              width:42px; height:42px; border-radius:50%; border:2px solid #C5D0E4;
              display:grid; place-items:center; color:#fff;
            }
            .socials a:hover{ background:var(--lemon); color:var(--ink); border-color:var(--lemon); }
            .pay-tags{ display:flex; flex-wrap:wrap; gap:8px; }
            .pay-tags span{ padding:6px 12px; border-radius:999px; background:rgba(255,255,255,.08); border:1px solid rgba(255,255,255,.2); font-size:13px; font-weight:600; color:#fff; }
            .foot-bottom{
              margin-top:60px; padding-top:24px; border-top:1px solid rgba(255,255,255,.18);
              display:flex; justify-content:space-between; align-items:center; gap:20px; flex-wrap:wrap; font-size:13px;
            }
            .age{
              display:inline-grid; place-items:center; width:34px; height:34px; margin-right:10px; vertical-align:middle;
              border-radius:50%; background:var(--red); color:#fff; font-weight:800; font-size:13px; border:2px solid #fff;
            }
            @media (max-width:900px){ .foot-grid{ grid-template-columns:repeat(2,1fr); } }
            @media (max-width:520px){ .foot-grid{ grid-template-columns:1fr; } }

            /* =========================================================
               12. INNER PAGE HERO (faq / cookies)
               ========================================================= */
            #page-hero{ background:var(--lemon); border-bottom:var(--line); padding:72px 0 64px; }
            #page-hero h1{ font-size:clamp(50px,8vw,100px); max-width:12ch; }
            #page-hero p{ margin-top:18px; max-width:52ch; font-size:19px; }
            .crumbs{ display:flex; gap:8px; font-size:14px; font-weight:600; margin-bottom:22px; color:var(--ink-soft); }
            .crumbs a{ text-decoration:none; }
            .crumbs a:hover{ text-decoration:underline; }
            .search{
              margin-top:32px; max-width:560px; display:flex; align-items:center; gap:10px;
              background:var(--card); border:var(--line); border-radius:999px; padding:6px 6px 6px 20px; box-shadow:var(--shadow);
            }
            .search svg{ flex:none; }
            .search input{ flex:1; min-width:0; border:0; background:none; padding:10px 4px; font-size:17px; color:var(--ink); }
            .search input:focus{ outline:none; }
            .search:focus-within{ outline:3px solid var(--pool); outline-offset:3px; }
            .page-meta{ margin-top:24px; display:flex; flex-wrap:wrap; gap:10px; }
            .page-meta span{ padding:6px 14px; border:var(--line); border-radius:999px; background:var(--card); font-size:14px; font-weight:600; }

            /* =========================================================
               14. COOKIES / POLICY CONTENT
               ========================================================= */
            #policy-content{ padding:72px 0 110px; }
            .policy-grid{ display:grid; grid-template-columns:1fr 340px; gap:56px; align-items:start; }
            .policy-text{ max-width:70ch; }
            .policy-text h2{ font-size:40px; margin:56px 0 16px; }
            .policy-text h2:first-child{ margin-top:0; }
            .policy-text p{ margin-bottom:16px; color:var(--ink-soft); }
            .policy-text ul{ margin:0 0 16px; display:flex; flex-direction:column; gap:10px; }
            .policy-text li{ position:relative; padding-left:30px; color:var(--ink-soft); }
            .policy-text li::before{
              content:""; position:absolute; left:0; top:6px; width:16px; height:16px; border-radius:50%;
              background:repeating-conic-gradient(var(--red) 0 30deg, #fff 30deg 60deg); border:2px solid var(--ink);
            }
            .cookie-types{ display:grid; gap:14px; margin:24px 0 8px; }
            .cookie-type{
              display:grid; grid-template-columns:150px 1fr auto; gap:20px; align-items:center;
              padding:18px 22px; background:var(--card); border:var(--line); border-radius:var(--r);
            }
            .cookie-type h3{ font-size:24px; }
            .cookie-type p{ margin:0; font-size:15px; }
            .cookie-type .life{ padding:4px 12px; border-radius:999px; background:var(--mint); border:var(--line); font-size:13px; font-weight:700; white-space:nowrap; }
            @media (max-width:640px){ .cookie-type{ grid-template-columns:1fr; gap:8px; } .cookie-type .life{ justify-self:start; } }

            .prefs{
              position:sticky; top:110px; background:var(--card); border:var(--line); border-radius:var(--r); box-shadow:var(--shadow); padding:26px;
            }
            .prefs h3{ font-size:30px; }
            .prefs > p{ margin-top:8px; font-size:15px; color:var(--ink-soft); }
            .pref{ display:flex; justify-content:space-between; align-items:center; gap:16px; padding:14px 0; border-top:2px dashed rgba(20,33,61,.3); font-weight:600; }
            .pref:first-of-type{ margin-top:18px; }
            .switch{ position:relative; width:52px; height:30px; flex:none; }
            .switch input{ position:absolute; opacity:0; inset:0; margin:0; cursor:pointer; z-index:1; }
            .switch span{ position:absolute; inset:0; border:var(--line); border-radius:999px; background:var(--paper); transition:background .2s; }
            .switch span::after{ content:""; position:absolute; top:3px; left:3px; width:20px; height:20px; border-radius:50%; background:var(--ink); transition:transform .2s var(--ease); }
            .switch input:checked + span{ background:var(--pool); }
            .switch input:checked + span::after{ transform:translateX(22px); background:#fff; }
            .switch input:disabled{ cursor:not-allowed; }
            .switch input:disabled + span{ opacity:.6; }
            .switch input:focus-visible + span{ outline:3px solid var(--pool); outline-offset:3px; }
            .prefs .pill{ margin-top:18px; }
            .prefs-status{ margin-top:10px; min-height:22px; font-size:14px; font-weight:600; color:var(--pool); }
            @media (max-width:960px){ .policy-grid{ grid-template-columns:1fr; } .prefs{ position:static; } }
            """;

    public static final String REGENT_CLUB_FAQ_STYLES = """
            /* =========================================================
               1. DESIGN TOKENS — edit these to re-skin the whole site
               ========================================================= */
            :root{
              /* --- colors --- */
              --paper:      #EEF3F7;
              --card:       #FFFFFF;
              --ink:        #14213D;
              --ink-soft:   #4A5875;
              --red:        #E63A2E;
              --red-dark:   #B8261D;
              --lemon:      #FFD447;
              --pool:       #22A6B3;
              --mint:       #A6E8D3;

              /* --- typography --- */
              --font-display: 'Bricolage Grotesque', 'Arial Narrow', Arial, sans-serif;
              --font-body:    'Figtree', 'Segoe UI', Arial, sans-serif;

              /* --- shape / depth --- */
              --line:       2px solid var(--ink);
              --shadow:     5px 5px 0 var(--ink);
              --shadow-sm:  3px 3px 0 var(--ink);
              --r-sm:       10px;
              --r:          18px;
              --r-lg:       28px;
              --ease:       cubic-bezier(.2,.9,.3,1);

              --container:  1200px;
            }

            @media (prefers-reduced-motion: reduce){
              *,*::before,*::after{ animation-duration:.001ms !important; animation-iteration-count:1 !important; transition-duration:.001ms !important; }
            }

            /* =========================================================
               2. RESET & BASE
               ========================================================= */
            *,*::before,*::after{ box-sizing:border-box; }
            html{ scroll-behavior:smooth; }
            body{
              margin:0;
              background:var(--paper);
              color:var(--ink);
              font-family:var(--font-body);
              font-size:17px;
              line-height:1.6;
              -webkit-font-smoothing:antialiased;
            }
            img{ max-width:100%; display:block; }
            a{ color:inherit; }
            ul{ margin:0; padding:0; list-style:none; }
            h1,h2,h3,h4{ margin:0; font-family:var(--font-display); font-weight:800; line-height:1.02; letter-spacing:-.02em; font-stretch:80%; font-variation-settings:"wdth" 80; }
            p{ margin:0; }
            button,input{ font:inherit; }
            button{ cursor:pointer; }
            :focus-visible{ outline:3px solid var(--pool); outline-offset:3px; }
            [hidden]{ display:none !important; }

            /* anchors land below the 76px sticky header */
            section[id], .faq-group{ scroll-margin-top:92px; }

            .wrap{ width:100%; max-width:var(--container); margin:0 auto; padding:0 24px; }
            .block{ padding:110px 0; }

            .sec-title{ font-size:clamp(40px,6vw,76px); max-width:14ch; }
            .sec-lead{ margin-top:18px; max-width:52ch; color:var(--ink-soft); font-size:18px; }

            /* buttons */
            .pill{
              display:inline-flex; align-items:center; justify-content:center; gap:8px;
              padding:14px 28px; border:var(--line); border-radius:999px;
              font-weight:700; font-size:16px; text-decoration:none; white-space:nowrap;
              background:var(--card); color:var(--ink); box-shadow:var(--shadow-sm);
              transition:transform .15s var(--ease), box-shadow .15s var(--ease), background .15s;
            }
            .pill:hover{ transform:translate(-2px,-2px); box-shadow:5px 5px 0 var(--ink); }
            .pill:active{ transform:translate(3px,3px); box-shadow:0 0 0 var(--ink); }
            .pill-red{ background:var(--red); color:#fff; }
            .pill-lemon{ background:var(--lemon); }
            .pill-sm{ padding:9px 18px; font-size:14px; }
            .pill-wide{ width:100%; }

            /* mini casino chip used in logo + decorations */
            .chip-mini{
              width:38px; height:38px; border-radius:50%; flex:none;
              background:repeating-conic-gradient(var(--red) 0 22.5deg, #fff 22.5deg 45deg);
              border:var(--line); position:relative;
            }
            .chip-mini::after{
              content:""; position:absolute; inset:6px; border-radius:50%;
              background:var(--red); border:2px solid #fff;
            }

            /* =========================================================
               3. HEADER
               ========================================================= */
            #site-header{
              position:sticky; top:0; z-index:100;
              background:var(--card);
              border-bottom:var(--line);
            }
            .bar{ display:flex; align-items:center; justify-content:space-between; gap:20px; height:76px; }
            .brand{ display:flex; align-items:center; gap:10px; text-decoration:none; font-family:var(--font-display); font-weight:800; font-size:28px; letter-spacing:-.03em; }
            .brand small{ font-family:var(--font-body); font-size:13px; font-weight:600; color:var(--ink-soft); letter-spacing:0; margin-left:2px; align-self:flex-end; padding-bottom:6px; }

            .menu{ display:flex; gap:6px; }
            .menu a{
              text-decoration:none; font-weight:600; font-size:15px;
              padding:8px 16px; border-radius:999px; border:2px solid transparent;
            }
            .menu a:hover{ border-color:var(--ink); }
            .menu a[aria-current="page"]{ background:var(--lemon); border-color:var(--ink); }

            .bar-actions{ display:flex; align-items:center; gap:10px; }
            .burger{
              display:none; width:46px; height:46px; border:var(--line); border-radius:50%;
              background:var(--card); align-items:center; justify-content:center; padding:0;
            }
            .burger span, .burger span::before, .burger span::after{
              display:block; width:18px; height:2px; background:var(--ink); position:relative; content:""; transition:transform .2s;
            }
            .burger span::before{ position:absolute; top:-6px; }
            .burger span::after{ position:absolute; top:6px; }
            .burger[aria-expanded="true"] span{ background:transparent; }
            .burger[aria-expanded="true"] span::before{ transform:translateY(6px) rotate(45deg); }
            .burger[aria-expanded="true"] span::after{ transform:translateY(-6px) rotate(-45deg); }

            @media (max-width:900px){
              .burger{ display:inline-flex; }
              .bar-actions .pill:not(.pill-red){ display:none; }
              .menu{
                position:absolute; left:0; right:0; top:100%;
                flex-direction:column; gap:4px; padding:16px 24px 24px;
                background:var(--card); border-bottom:var(--line);
                display:none;
              }
              .menu.is-open{ display:flex; }
              .menu a{ padding:12px 16px; font-size:17px; }
            }
            @media (max-width:420px){ .bar-actions .pill-red{ display:none; } }

            /* =========================================================
               9. FAQ (home preview + full page items)
               ========================================================= */
            .qa-list{ display:flex; flex-direction:column; gap:14px; }
            .qa{ background:var(--card); border:var(--line); border-radius:var(--r); transition:box-shadow .15s; }
            .qa[open]{ box-shadow:var(--shadow); }
            .qa summary{
              list-style:none; cursor:pointer; display:flex; justify-content:space-between; align-items:center; gap:20px;
              padding:20px 24px; font-weight:700; font-size:18px;
            }
            .qa summary::-webkit-details-marker{ display:none; }
            .qa summary::after{
              content:"+"; flex:none; width:32px; height:32px; border:var(--line); border-radius:50%;
              display:grid; place-items:center; font-size:20px; line-height:1; transition:transform .2s var(--ease), background .2s;
            }
            .qa[open] summary::after{ transform:rotate(45deg); background:var(--lemon); }
            .qa p{ padding:0 24px 22px; color:var(--ink-soft); max-width:62ch; }

            /* =========================================================
               11. FOOTER
               ========================================================= */
            #site-footer{ background:var(--ink); color:#C5D0E4; padding:80px 0 32px; border-top:var(--line); }
            .foot-grid{ display:grid; grid-template-columns:1.5fr 1fr 1fr 1.3fr; gap:40px; }
            #site-footer .brand{ color:#fff; font-size:28px; text-decoration:none; }
            #site-footer .brand small{ color:#C5D0E4; }
            .foot-about{ margin-top:16px; max-width:34ch; font-size:15px; }
            .foot-grid h4{ color:#fff; font-size:22px; margin-bottom:14px; }
            .foot-grid ul{ display:flex; flex-direction:column; gap:8px; }
            .foot-grid a{ text-decoration:none; font-size:15px; }
            .foot-grid a:hover{ color:var(--lemon); text-decoration:underline; }
            .socials{ display:flex; gap:10px; margin-top:22px; }
            .socials a{
              width:42px; height:42px; border-radius:50%; border:2px solid #C5D0E4;
              display:grid; place-items:center; color:#fff;
            }
            .socials a:hover{ background:var(--lemon); color:var(--ink); border-color:var(--lemon); }
            .pay-tags{ display:flex; flex-wrap:wrap; gap:8px; }
            .pay-tags span{ padding:6px 12px; border-radius:999px; background:rgba(255,255,255,.08); border:1px solid rgba(255,255,255,.2); font-size:13px; font-weight:600; color:#fff; }
            .foot-bottom{
              margin-top:60px; padding-top:24px; border-top:1px solid rgba(255,255,255,.18);
              display:flex; justify-content:space-between; align-items:center; gap:20px; flex-wrap:wrap; font-size:13px;
            }
            .age{
              display:inline-grid; place-items:center; width:34px; height:34px; margin-right:10px; vertical-align:middle;
              border-radius:50%; background:var(--red); color:#fff; font-weight:800; font-size:13px; border:2px solid #fff;
            }
            @media (max-width:900px){ .foot-grid{ grid-template-columns:repeat(2,1fr); } }
            @media (max-width:520px){ .foot-grid{ grid-template-columns:1fr; } }

            /* =========================================================
               12. INNER PAGE HERO (faq / cookies)
               ========================================================= */
            #page-hero{ background:var(--lemon); border-bottom:var(--line); padding:72px 0 64px; }
            #page-hero h1{ font-size:clamp(50px,8vw,100px); max-width:12ch; }
            #page-hero p{ margin-top:18px; max-width:52ch; font-size:19px; }
            .crumbs{ display:flex; gap:8px; font-size:14px; font-weight:600; margin-bottom:22px; color:var(--ink-soft); }
            .crumbs a{ text-decoration:none; }
            .crumbs a:hover{ text-decoration:underline; }
            .search{
              margin-top:32px; max-width:560px; display:flex; align-items:center; gap:10px;
              background:var(--card); border:var(--line); border-radius:999px; padding:6px 6px 6px 20px; box-shadow:var(--shadow);
            }
            .search svg{ flex:none; }
            .search input{ flex:1; min-width:0; border:0; background:none; padding:10px 4px; font-size:17px; color:var(--ink); }
            .search input:focus{ outline:none; }
            .search:focus-within{ outline:3px solid var(--pool); outline-offset:3px; }
            .page-meta{ margin-top:24px; display:flex; flex-wrap:wrap; gap:10px; }
            .page-meta span{ padding:6px 14px; border:var(--line); border-radius:999px; background:var(--card); font-size:14px; font-weight:600; }

            /* =========================================================
               13. FAQ PAGE CONTENT
               ========================================================= */
            #faq-content{ padding:64px 0 110px; }
            .filters{ display:flex; flex-wrap:wrap; gap:10px; margin-bottom:48px; }
            .filter{
              padding:9px 18px; border:var(--line); border-radius:999px; background:var(--card); color:var(--ink);
              font-weight:600; font-size:15px;
            }
            .filter[aria-pressed="true"]{ background:var(--ink); color:#fff; }
            .faq-group{ display:grid; grid-template-columns:260px 1fr; gap:40px; padding:40px 0; border-top:var(--line); }
            .filters + .faq-group{ border-top:0; padding-top:0; }
            .faq-group h2{ font-size:40px; }
            .faq-group .count{ display:block; margin-top:8px; font-family:var(--font-body); font-size:15px; font-weight:600; color:var(--ink-soft); letter-spacing:0; }
            .faq-empty{ display:none; padding:40px; border:2px dashed var(--ink); border-radius:var(--r); text-align:center; font-weight:600; }
            .faq-empty.is-visible{ display:block; }
            @media (max-width:860px){ .faq-group{ grid-template-columns:1fr; gap:20px; } }
            """;
}