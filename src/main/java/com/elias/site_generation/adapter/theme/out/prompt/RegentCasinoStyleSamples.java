package com.elias.site_generation.adapter.theme.out.prompt;

public final class RegentCasinoStyleSamples {

    public static final String REGENT_CLUB_HOME_PAGE_STYLES = """
            /* =========================================================
                 1. DESIGN TOKENS — edit these to re-skin the whole page
              ========================================================= */
              :root{
                /* --- colors --- */
                --bg:            #0c1712;
                --bg-alt:        #0f1d17;
                --surface:       #16261e;
                --surface-2:     #1c3226;
                --border:        #2c4536;
                --text:          #f3eee0;
                --text-muted:    #a4b3a9;
                --brass:         #cda43f;
                --brass-light:   #ecd074;
                --ruby:          #a5304a;
                --emerald:       #2f8f63;
                --success:       #55c090;

                /* --- typography --- */
                --font-display:  'Fraunces', 'Georgia', serif;
                --font-body:     'Inter', system-ui, sans-serif;

                /* --- shape / motion --- */
                --radius-sm:     6px;
                --radius:        14px;
                --radius-lg:     22px;
                --ease:          cubic-bezier(.22,1,.36,1);

                --container:     1180px;
              }

              @media (prefers-reduced-motion: reduce){
                *{ animation-duration: .001ms !important; animation-iteration-count: 1 !important; transition-duration: .001ms !important; }
              }

              /* =========================================================
                 2. RESET & BASE
              ========================================================= */
              *,*::before,*::after{ box-sizing: border-box; }
              html{ scroll-behavior: smooth; }
              body{
                margin:0;
                background: var(--bg);
                color: var(--text);
                font-family: var(--font-body);
                font-size: 16px;
                line-height: 1.65;
                -webkit-font-smoothing: antialiased;
              }
              img{ max-width:100%; display:block; }
              a{ color: inherit; text-decoration: none; }
              ul{ list-style: none; margin:0; padding:0; }
              h1,h2,h3,h4{ margin:0; font-family: var(--font-display); font-weight:600; line-height:1.12; }
              p{ margin:0; }
              button{ font-family: inherit; cursor:pointer; }
              :focus-visible{ outline: 2px solid var(--brass-light); outline-offset: 3px; }

              .container{ width:100%; max-width: var(--container); margin:0 auto; padding: 0 24px; }
              .section{ padding: 100px 0; }
              .section-alt{ background: var(--bg-alt); }

              /* small-caps label with a suit glyph, used instead of a generic dot eyebrow */
              .label{
                display:inline-flex; align-items:center; gap:9px;
                font-size: 13px; font-weight:600; letter-spacing:.05em;
                color: var(--brass-light);
                font-variant: small-caps;
              }
              .label .suit{ font-size:15px; color: var(--ruby); }

              .divider{
                display:flex; align-items:center; justify-content:center; gap:14px;
                margin: 0 0 20px;
              }
              .divider::before, .divider::after{ content:''; height:1px; width:46px; background: var(--border); }
              .divider .suit{ color: var(--brass); font-size:15px; }

              .section-head{ max-width: 600px; margin: 0 0 52px; }
              .section-head h2{ font-size: clamp(28px,3.6vw,40px); margin-top:12px; }
              .section-head p{ margin-top:14px; color: var(--text-muted); font-size: 16.5px; }
              .section-head.center{ margin-left:auto; margin-right:auto; text-align:center; }

              .btn{
                display:inline-flex; align-items:center; justify-content:center; gap:8px;
                padding: 14px 30px; border-radius: 3px; border: 1px solid transparent;
                font-weight:600; font-size:14.5px; letter-spacing:.03em;
                transition: transform .2s var(--ease), box-shadow .2s var(--ease), background .2s var(--ease), border-color .2s var(--ease);
                white-space:nowrap; font-family: var(--font-body);
              }
              .btn-primary{ background: linear-gradient(135deg, var(--brass-light), var(--brass)); color:#211705; }
              .btn-primary:hover{ transform: translateY(-2px); box-shadow: 0 14px 30px rgba(205,164,63,.28); }
              .btn-ghost{ background: transparent; border-color: var(--border); color: var(--text); }
              .btn-ghost:hover{ border-color: var(--brass); color: var(--brass-light); }
              .btn-block{ width:100%; }
              .btn-sm{ padding: 10px 20px; font-size:13px; }

              /* =========================================================
                 3. HEADER
              ========================================================= */
              #site-header{
                position: sticky; top:0; z-index: 100;
                background: rgba(12,23,18,.85);
                backdrop-filter: blur(14px);
                border-bottom: 1px solid var(--border);
              }
              .nav-row{ display:flex; align-items:center; justify-content:space-between; height: 80px; gap: 24px; }
              .logo{ display:flex; align-items:center; gap:10px; font-family: var(--font-display); font-size:22px; }
              .logo-mark{
                width:36px; height:36px; border-radius: 3px;
                background: linear-gradient(135deg, var(--brass-light), var(--brass));
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); color:#20160a; font-size:17px; font-weight:700;
              }
              .logo .accent{ color: var(--brass-light); }

              .nav-links{ display:flex; align-items:center; gap: 34px; }
              .nav-links a{
                font-size:14.5px; font-weight:500; color: var(--text-muted);
                transition: color .2s var(--ease);
              }
              .nav-links a:hover{ color: var(--text); }

              .nav-actions{ display:flex; align-items:center; gap:12px; }
              .nav-toggle{
                display:none; width:42px; height:42px; border-radius: var(--radius-sm);
                background: var(--surface); border:1px solid var(--border);
                align-items:center; justify-content:center;
              }
              .nav-toggle span, .nav-toggle span::before, .nav-toggle span::after{
                content:''; display:block; width:18px; height:2px; background: var(--text); position:relative;
                transition: transform .2s var(--ease), opacity .2s var(--ease);
              }
              .nav-toggle span::before{ position:absolute; top:-6px; }
              .nav-toggle span::after{ position:absolute; top:6px; }

              @media (max-width: 880px){
                .nav-links{
                  position:absolute; top:80px; left:0; right:0;
                  flex-direction:column; align-items:flex-start; gap:0;
                  background: var(--bg-alt); border-bottom:1px solid var(--border);
                  max-height:0; overflow:hidden; transition: max-height .3s var(--ease);
                }
                .nav-links.open{ max-height: 320px; }
                .nav-links a{ width:100%; padding: 16px 24px; border-top:1px solid var(--border); }
                .nav-toggle{ display:flex; }
                body.nav-open .nav-toggle span{ transform: scaleX(0); }
                body.nav-open .nav-toggle span::before{ transform: rotate(45deg) translate(4px,5px); }
                body.nav-open .nav-toggle span::after{ transform: rotate(-45deg) translate(4px,-5px); }
                .nav-actions .btn-ghost{ display:none; }
              }

              /* =========================================================
                 4. HERO + signature roulette wheel
              ========================================================= */
              #hero{
                position:relative; overflow:hidden;
                padding: 92px 0 104px;
                background:
                  radial-gradient(640px 420px at 88% 10%, rgba(47,143,99,.22), transparent 65%),
                  radial-gradient(520px 460px at 10% 100%, rgba(205,164,63,.10), transparent 60%),
                  var(--bg);
              }
              .hero-grid{ display:grid; grid-template-columns: 1.05fr .95fr; gap: 60px; align-items:center; }
              @media (max-width: 900px){ .hero-grid{ grid-template-columns: 1fr; } }

              .hero-copy h1{ font-size: clamp(36px,5vw,58px); margin-top:16px; }
              .hero-copy p{ margin-top:20px; color: var(--text-muted); font-size:17px; max-width:480px; }
              .hero-actions{ display:flex; gap:14px; margin-top:34px; flex-wrap:wrap; }
              .hero-meta{ display:flex; gap:36px; margin-top:44px; flex-wrap:wrap; }
              .hero-meta div strong{ display:block; font-family: var(--font-display); font-size:24px; color: var(--brass-light); }
              .hero-meta div span{ display:block; margin-top:4px; font-size:12.5px; color: var(--text-muted); }

              .hero-visual{ display:flex; align-items:center; justify-content:center; position:relative; }
              .wheel-stage{ position:relative; width: 340px; height: 340px; }
              .wheel-rim{
                position:absolute; inset:0; border-radius:50%;
                background: linear-gradient(150deg, var(--brass-light), var(--brass) 55%, #6f551d);
                padding: 10px;
                box-shadow: 0 30px 70px rgba(0,0,0,.45);
              }
              .wheel{
                width:100%; height:100%; border-radius:50%;
                background: conic-gradient(
                  var(--ruby) 0deg 30deg, #14251c 30deg 60deg,
                  var(--emerald) 60deg 90deg, #14251c 90deg 120deg,
                  var(--ruby) 120deg 150deg, #14251c 150deg 180deg,
                  var(--emerald) 180deg 210deg, #14251c 210deg 240deg,
                  var(--ruby) 240deg 270deg, #14251c 270deg 300deg,
                  var(--emerald) 300deg 330deg, #14251c 330deg 360deg
                );
                animation: spin 14s linear infinite;
                position:relative;
              }
              @keyframes spin{ to{ transform: rotate(360deg); } }
              .wheel-hub{
                position:absolute; top:50%; left:50%; transform: translate(-50%,-50%);
                width:92px; height:92px; border-radius:50%;
                background: radial-gradient(circle at 35% 30%, var(--brass-light), var(--brass) 60%, #6f551d);
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); font-size:26px; color:#20160a; font-weight:700;
                box-shadow: 0 8px 22px rgba(0,0,0,.4);
              }
              .wheel-ball{
                position:absolute; top:14px; left:50%; width:12px; height:12px; border-radius:50%;
                background: #f4efe0; box-shadow: 0 0 8px rgba(244,239,224,.8);
                animation: orbit 14s linear infinite reverse;
                transform-origin: 6px 156px;
              }
              @keyframes orbit{ from{ transform: rotate(0deg) translate(0,0);} to{ transform: rotate(360deg) translate(0,0);} }

              .chip{
                position:absolute; width:52px; height:52px; border-radius:50%;
                border: 4px dashed rgba(244,239,224,.85);
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); font-size:13px; color: var(--text);
                box-shadow: 0 12px 24px rgba(0,0,0,.4);
              }
              .chip-1{ background: var(--ruby); top:-6px; right:6px; }
              .chip-2{ background: var(--emerald); bottom:6px; left:-16px; }
              .chip-3{ background: #3a2c12; border-color: rgba(236,208,116,.9); bottom:-14px; right:36px; width:42px; height:42px; font-size:11px; }

              .win-pill{
                position:absolute; bottom:-8px; left:50%; transform: translateX(-50%);
                background: var(--surface); border:1px solid var(--border); border-radius: 999px;
                padding: 10px 20px; display:flex; align-items:center; gap:10px;
                font-size:13px; color: var(--text-muted); white-space:nowrap;
              }
              .win-pill .amt{ color: var(--brass-light); font-weight:700; }
              .win-pill .pulse-dot{ width:7px; height:7px; border-radius:50%; background: var(--success); box-shadow: 0 0 10px var(--success); }

              /* =========================================================
                 5. STATS
              ========================================================= */
              #stats{ border-top:1px solid var(--border); border-bottom:1px solid var(--border); }
              .stats-grid{ display:grid; grid-template-columns: repeat(4,1fr); gap: 24px; text-align:center; padding: 44px 0; }
              @media (max-width: 760px){ .stats-grid{ grid-template-columns: repeat(2,1fr); } }
              .stat h3{ font-size: clamp(24px,3vw,32px); color: var(--brass-light); }
              .stat span{ display:block; margin-top:6px; font-size:12.5px; color: var(--text-muted); }

              /* =========================================================
                 6. FEATURES
              ========================================================= */
              .features-grid{ display:grid; grid-template-columns: repeat(4,1fr); gap:1px; background: var(--border); border:1px solid var(--border); border-radius: var(--radius); overflow:hidden; }
              @media (max-width: 980px){ .features-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 560px){ .features-grid{ grid-template-columns: 1fr; } }
              .feature-card{
                background: var(--surface); padding: 32px 26px;
                transition: background .25s var(--ease);
              }
              .feature-card:hover{ background: var(--surface-2); }
              .feature-icon{
                width:42px; height:42px; border-radius: var(--radius-sm); margin-bottom:20px;
                display:flex; align-items:center; justify-content:center;
                border: 1px solid var(--border);
                color: var(--brass-light);
              }
              .feature-card h3{ font-size:17px; font-family: var(--font-body); font-weight:700; }
              .feature-card p{ margin-top:10px; font-size:14.5px; color: var(--text-muted); }

              /* =========================================================
                 7. GAMES GRID
              ========================================================= */
              .games-grid{ display:grid; grid-template-columns: repeat(3,1fr); gap:22px; }
              @media (max-width: 980px){ .games-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 620px){ .games-grid{ grid-template-columns: 1fr; } }
              .game-card{
                background: var(--surface); border:1px solid var(--border);
                border-radius: 18px 6px 18px 6px;
                overflow:hidden; transition: transform .25s var(--ease), border-color .25s var(--ease);
              }
              .game-card:hover{ transform: translateY(-4px); border-color: var(--brass); }
              .game-thumb{
                height: 150px; display:flex; align-items:center; justify-content:center;
                position:relative; background: var(--surface-2);
              }
              .game-thumb .tag{
                position:absolute; top:12px; left:12px; font-size:11px; font-weight:700;
                background: rgba(0,0,0,.45); backdrop-filter: blur(4px);
                padding: 5px 11px; border-radius: 999px; color: var(--brass-light);
                border: 1px solid rgba(205,164,63,.4);
              }
              .game-body{ padding: 18px 20px 22px; }
              .game-body h3{ font-size:16px; font-family: var(--font-body); font-weight:700; }
              .game-meta{ display:flex; justify-content:space-between; margin-top:8px; font-size:12.5px; color: var(--text-muted); }
              .game-body .btn{ margin-top:16px; width:100%; }

              /* =========================================================
                 8. VIP TIERS
              ========================================================= */
              .tiers-grid{ display:grid; grid-template-columns: repeat(3,1fr); gap:20px; }
              @media (max-width: 860px){ .tiers-grid{ grid-template-columns: 1fr; } }
              .tier-card{
                padding: 32px 28px; border-radius: var(--radius); border:1px solid var(--border);
                background: var(--surface); position:relative;
              }
              .tier-card.is-featured{
                border-color: var(--brass); background: linear-gradient(160deg, var(--surface-2), var(--surface));
              }
              .tier-name{ font-family: var(--font-display); font-size:22px; color: var(--brass-light); }
              .tier-req{ margin-top:6px; font-size:13px; color: var(--text-muted); }
              .tier-perks{ margin-top:20px; display:flex; flex-direction:column; gap:10px; }
              .tier-perks li{ display:flex; gap:10px; font-size:14.5px; color: var(--text); align-items:flex-start; }
              .tier-perks li::before{ content:'♦'; color: var(--ruby); flex-shrink:0; }

              /* =========================================================
                 9. JACKPOT BANNER
              ========================================================= */
              #jackpot{
                margin: 0 24px; max-width: calc(var(--container) - 0px); margin-left:auto; margin-right:auto;
                border-radius: var(--radius-lg);
                background: linear-gradient(120deg, #142a1f, #1d3826 45%, #142a1f);
                border: 1px solid rgba(205,164,63,.35);
                padding: 56px 48px;
                display:flex; align-items:center; justify-content:space-between; gap:32px; flex-wrap:wrap;
                position:relative; overflow:hidden;
              }
              #jackpot::before{
                content:''; position:absolute; inset:0;
                background: radial-gradient(420px 240px at 85% 0%, rgba(205,164,63,.18), transparent 70%);
              }
              .jackpot-copy{ position:relative; z-index:1; max-width: 460px; }
              .jackpot-amount{
                font-family: var(--font-display); font-size: clamp(38px,5.6vw,58px); margin-top:12px;
                color: var(--brass-light);
              }
              .jackpot-copy p{ margin-top:10px; color: rgba(243,238,224,.8); }
              .countdown{ position:relative; z-index:1; display:flex; gap:14px; }
              .countdown .box{
                width:78px; padding: 14px 0; text-align:center; border-radius: var(--radius-sm);
                background: rgba(0,0,0,.28); border:1px solid rgba(205,164,63,.25);
              }
              .countdown .box strong{ display:block; font-family: var(--font-display); font-size:26px; color:#fff; }
              .countdown .box span{ font-size:11px; color: var(--text-muted); }

              /* =========================================================
                 10. FAQ (index preview + full page)
              ========================================================= */
              .faq-list{ max-width: 760px; margin: 0 auto; display:flex; flex-direction:column; gap:12px; }
              .faq-item{ background: var(--surface); border:1px solid var(--border); border-radius: var(--radius); overflow:hidden; }
              .faq-item summary{
                list-style:none; cursor:pointer; padding: 20px 24px; display:flex; align-items:center; justify-content:space-between;
                font-weight:600; font-size:15.5px;
              }
              .faq-item summary::-webkit-details-marker{ display:none; }
              .faq-item summary::after{
                content:'+'; font-family: var(--font-display); font-size:22px; color: var(--brass); transition: transform .2s var(--ease);
              }
              .faq-item[open] summary::after{ transform: rotate(45deg); }
              .faq-item .faq-a{ padding: 0 24px 20px; color: var(--text-muted); font-size:14.5px; }

              /* =========================================================
                 11. CTA / SIGNUP BAND
              ========================================================= */
              #cta{
                text-align:center; padding: 92px 0;
                background:
                  radial-gradient(600px 300px at 50% 0%, rgba(165,48,74,.14), transparent 70%),
                  var(--bg-alt);
                border-top: 1px solid var(--border);
              }
              #cta h2{ font-size: clamp(28px,4.2vw,42px); }
              #cta p{ margin-top:14px; color: var(--text-muted); max-width:480px; margin-left:auto; margin-right:auto; }
              .cta-form{ margin-top: 30px; display:flex; gap:10px; max-width: 420px; margin-left:auto; margin-right:auto; }
              .cta-form input{
                flex:1; padding: 14px 18px; border-radius: 3px; border:1px solid var(--border);
                background: var(--surface); color: var(--text); font-family: inherit; font-size:14.5px;
              }
              .cta-form input:focus{ border-color: var(--brass); }
              @media (max-width: 480px){ .cta-form{ flex-direction:column; } }

              /* =========================================================
                 12. FOOTER
              ========================================================= */
              #site-footer{ padding: 72px 0 28px; background: var(--bg); }
              .footer-grid{ display:grid; grid-template-columns: 1.4fr 1fr 1fr 1.2fr; gap: 40px; }
              @media (max-width: 860px){ .footer-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 520px){ .footer-grid{ grid-template-columns: 1fr; } }
              .footer-grid h4{ font-family: var(--font-body); font-size:13px; text-transform:uppercase; letter-spacing:.08em; color: var(--text-muted); margin-bottom:16px; }
              .footer-grid ul{ display:flex; flex-direction:column; gap:10px; }
              .footer-grid a{ font-size:14.5px; color: var(--text-muted); transition: color .2s var(--ease); }
              .footer-grid a:hover{ color: var(--brass-light); }
              .footer-brand p{ margin-top:14px; color: var(--text-muted); font-size:14px; max-width:280px; }
              .social-row{ display:flex; gap:10px; margin-top:20px; }
              .social-row a{
                width:38px; height:38px; border-radius:50%; border:1px solid var(--border);
                display:flex; align-items:center; justify-content:center; color: var(--text);
              }
              .social-row a:hover{ border-color: var(--brass); color: var(--brass-light); }
              .payment-row{ display:flex; flex-wrap:wrap; gap:8px; }
              .payment-row span{
                padding: 7px 12px; border-radius: var(--radius-sm); background: var(--surface); border:1px solid var(--border);
                font-size:12.5px; color: var(--text-muted); font-weight:600;
              }
              .footer-bottom{
                margin-top: 56px; padding-top: 24px; border-top:1px solid var(--border);
                display:flex; justify-content:space-between; gap:20px; flex-wrap:wrap;
                font-size:12.5px; color: var(--text-muted);
              }
              .footer-bottom .age-badge{
                display:inline-flex; align-items:center; justify-content:center;
                width:26px; height:26px; border-radius:6px; border:1px solid var(--border); margin-right:8px;
                font-weight:700; color: var(--brass-light);
              }
            """;

    public static final String REGENT_CLUB_COOKIES_PAGE_STYLES = """
            /* =========================================================
                 1. DESIGN TOKENS — edit these to re-skin the whole page
              ========================================================= */
              :root{
                /* --- colors --- */
                --bg:            #0c1712;
                --bg-alt:        #0f1d17;
                --surface:       #16261e;
                --surface-2:     #1c3226;
                --border:        #2c4536;
                --text:          #f3eee0;
                --text-muted:    #a4b3a9;
                --brass:         #cda43f;
                --brass-light:   #ecd074;
                --ruby:          #a5304a;
                --emerald:       #2f8f63;
                --success:       #55c090;

                /* --- typography --- */
                --font-display:  'Fraunces', 'Georgia', serif;
                --font-body:     'Inter', system-ui, sans-serif;

                /* --- shape / motion --- */
                --radius-sm:     6px;
                --radius:        14px;
                --radius-lg:     22px;
                --ease:          cubic-bezier(.22,1,.36,1);

                --container:     1180px;
              }

              @media (prefers-reduced-motion: reduce){
                *{ animation-duration: .001ms !important; animation-iteration-count: 1 !important; transition-duration: .001ms !important; }
              }

              /* =========================================================
                 2. RESET & BASE
              ========================================================= */
              *,*::before,*::after{ box-sizing: border-box; }
              html{ scroll-behavior: smooth; }
              body{
                margin:0;
                background: var(--bg);
                color: var(--text);
                font-family: var(--font-body);
                font-size: 16px;
                line-height: 1.65;
                -webkit-font-smoothing: antialiased;
              }
              img{ max-width:100%; display:block; }
              a{ color: inherit; text-decoration: none; }
              ul{ list-style: none; margin:0; padding:0; }
              h1,h2,h3,h4{ margin:0; font-family: var(--font-display); font-weight:600; line-height:1.12; }
              p{ margin:0; }
              button{ font-family: inherit; cursor:pointer; }
              :focus-visible{ outline: 2px solid var(--brass-light); outline-offset: 3px; }

              .container{ width:100%; max-width: var(--container); margin:0 auto; padding: 0 24px; }
              .section{ padding: 100px 0; }
              .section-alt{ background: var(--bg-alt); }

              /* small-caps label with a suit glyph, used instead of a generic dot eyebrow */
              .label{
                display:inline-flex; align-items:center; gap:9px;
                font-size: 13px; font-weight:600; letter-spacing:.05em;
                color: var(--brass-light);
                font-variant: small-caps;
              }
              .label .suit{ font-size:15px; color: var(--ruby); }

              .divider{
                display:flex; align-items:center; justify-content:center; gap:14px;
                margin: 0 0 20px;
              }
              .divider::before, .divider::after{ content:''; height:1px; width:46px; background: var(--border); }
              .divider .suit{ color: var(--brass); font-size:15px; }

              .section-head{ max-width: 600px; margin: 0 0 52px; }
              .section-head h2{ font-size: clamp(28px,3.6vw,40px); margin-top:12px; }
              .section-head p{ margin-top:14px; color: var(--text-muted); font-size: 16.5px; }
              .section-head.center{ margin-left:auto; margin-right:auto; text-align:center; }

              .btn{
                display:inline-flex; align-items:center; justify-content:center; gap:8px;
                padding: 14px 30px; border-radius: 3px; border: 1px solid transparent;
                font-weight:600; font-size:14.5px; letter-spacing:.03em;
                transition: transform .2s var(--ease), box-shadow .2s var(--ease), background .2s var(--ease), border-color .2s var(--ease);
                white-space:nowrap; font-family: var(--font-body);
              }
              .btn-primary{ background: linear-gradient(135deg, var(--brass-light), var(--brass)); color:#211705; }
              .btn-primary:hover{ transform: translateY(-2px); box-shadow: 0 14px 30px rgba(205,164,63,.28); }
              .btn-ghost{ background: transparent; border-color: var(--border); color: var(--text); }
              .btn-ghost:hover{ border-color: var(--brass); color: var(--brass-light); }
              .btn-block{ width:100%; }
              .btn-sm{ padding: 10px 20px; font-size:13px; }

              /* =========================================================
                 3. HEADER
              ========================================================= */
              #site-header{
                position: sticky; top:0; z-index: 100;
                background: rgba(12,23,18,.85);
                backdrop-filter: blur(14px);
                border-bottom: 1px solid var(--border);
              }
              .nav-row{ display:flex; align-items:center; justify-content:space-between; height: 80px; gap: 24px; }
              .logo{ display:flex; align-items:center; gap:10px; font-family: var(--font-display); font-size:22px; }
              .logo-mark{
                width:36px; height:36px; border-radius: 3px;
                background: linear-gradient(135deg, var(--brass-light), var(--brass));
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); color:#20160a; font-size:17px; font-weight:700;
              }
              .logo .accent{ color: var(--brass-light); }

              .nav-links{ display:flex; align-items:center; gap: 34px; }
              .nav-links a{
                font-size:14.5px; font-weight:500; color: var(--text-muted);
                transition: color .2s var(--ease);
              }
              .nav-links a:hover{ color: var(--text); }

              .nav-actions{ display:flex; align-items:center; gap:12px; }
              .nav-toggle{
                display:none; width:42px; height:42px; border-radius: var(--radius-sm);
                background: var(--surface); border:1px solid var(--border);
                align-items:center; justify-content:center;
              }
              .nav-toggle span, .nav-toggle span::before, .nav-toggle span::after{
                content:''; display:block; width:18px; height:2px; background: var(--text); position:relative;
                transition: transform .2s var(--ease), opacity .2s var(--ease);
              }
              .nav-toggle span::before{ position:absolute; top:-6px; }
              .nav-toggle span::after{ position:absolute; top:6px; }

              @media (max-width: 880px){
                .nav-links{
                  position:absolute; top:80px; left:0; right:0;
                  flex-direction:column; align-items:flex-start; gap:0;
                  background: var(--bg-alt); border-bottom:1px solid var(--border);
                  max-height:0; overflow:hidden; transition: max-height .3s var(--ease);
                }
                .nav-links.open{ max-height: 320px; }
                .nav-links a{ width:100%; padding: 16px 24px; border-top:1px solid var(--border); }
                .nav-toggle{ display:flex; }
                body.nav-open .nav-toggle span{ transform: scaleX(0); }
                body.nav-open .nav-toggle span::before{ transform: rotate(45deg) translate(4px,5px); }
                body.nav-open .nav-toggle span::after{ transform: rotate(-45deg) translate(4px,-5px); }
                .nav-actions .btn-ghost{ display:none; }
              }

              #page-hero{
                padding: 70px 0 56px; border-bottom:1px solid var(--border);
                background: radial-gradient(600px 320px at 80% 0%, rgba(47,143,99,.16), transparent 65%), var(--bg);
              }
              .page-hero-inner{ max-width:640px; }
              .page-hero-inner h1{ font-size: clamp(32px,4.6vw,46px); margin-top:14px; }
              .page-hero-inner p{ margin-top:16px; color: var(--text-muted); font-size:16px; }

              /* =========================================================
                 12. FOOTER
              ========================================================= */
              #site-footer{ padding: 72px 0 28px; background: var(--bg); }
              .footer-grid{ display:grid; grid-template-columns: 1.4fr 1fr 1fr 1.2fr; gap: 40px; }
              @media (max-width: 860px){ .footer-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 520px){ .footer-grid{ grid-template-columns: 1fr; } }
              .footer-grid h4{ font-family: var(--font-body); font-size:13px; text-transform:uppercase; letter-spacing:.08em; color: var(--text-muted); margin-bottom:16px; }
              .footer-grid ul{ display:flex; flex-direction:column; gap:10px; }
              .footer-grid a{ font-size:14.5px; color: var(--text-muted); transition: color .2s var(--ease); }
              .footer-grid a:hover{ color: var(--brass-light); }
              .footer-brand p{ margin-top:14px; color: var(--text-muted); font-size:14px; max-width:280px; }
              .social-row{ display:flex; gap:10px; margin-top:20px; }
              .social-row a{
                width:38px; height:38px; border-radius:50%; border:1px solid var(--border);
                display:flex; align-items:center; justify-content:center; color: var(--text);
              }
              .social-row a:hover{ border-color: var(--brass); color: var(--brass-light); }
              .payment-row{ display:flex; flex-wrap:wrap; gap:8px; }
              .payment-row span{
                padding: 7px 12px; border-radius: var(--radius-sm); background: var(--surface); border:1px solid var(--border);
                font-size:12.5px; color: var(--text-muted); font-weight:600;
              }
              .footer-bottom{
                margin-top: 56px; padding-top: 24px; border-top:1px solid var(--border);
                display:flex; justify-content:space-between; gap:20px; flex-wrap:wrap;
                font-size:12.5px; color: var(--text-muted);
              }
              .footer-bottom .age-badge{
                display:inline-flex; align-items:center; justify-content:center;
                width:26px; height:26px; border-radius:6px; border:1px solid var(--border); margin-right:8px;
                font-weight:700; color: var(--brass-light);
              }

              /* =========================================================
                 13. LEGAL / COOKIES PAGE CONTENT
              ========================================================= */
              .legal-content{ max-width: 760px; margin: 0 auto; }
              .legal-content h2{ font-size:22px; margin-top:40px; margin-bottom:14px; color: var(--brass-light); }
              .legal-content h2:first-child{ margin-top:0; }
              .legal-content p{ color: var(--text-muted); font-size:15px; margin-bottom:14px; }
              .legal-content ul{ display:flex; flex-direction:column; gap:8px; margin: 14px 0; }
              .legal-content li{ color: var(--text-muted); font-size:15px; padding-left:18px; position:relative; }
              .legal-content li::before{ content:'♦'; position:absolute; left:0; color: var(--ruby); font-size:11px; top:5px; }
              .legal-table{ width:100%; border-collapse: collapse; margin: 18px 0 26px; font-size:14px; }
              .legal-table th, .legal-table td{ text-align:left; padding: 12px 14px; border-bottom:1px solid var(--border); color: var(--text-muted); }
              .legal-table th{ color: var(--text); font-weight:600; }
              .legal-updated{ font-size:13px; color: var(--text-muted); margin-bottom:36px; }
            """;

    public static final String REGENT_CLUB_FAQ_STYLES = """
            /* =========================================================
                 1. DESIGN TOKENS — edit these to re-skin the whole page
              ========================================================= */
              :root{
                /* --- colors --- */
                --bg:            #0c1712;
                --bg-alt:        #0f1d17;
                --surface:       #16261e;
                --surface-2:     #1c3226;
                --border:        #2c4536;
                --text:          #f3eee0;
                --text-muted:    #a4b3a9;
                --brass:         #cda43f;
                --brass-light:   #ecd074;
                --ruby:          #a5304a;
                --emerald:       #2f8f63;
                --success:       #55c090;

                /* --- typography --- */
                --font-display:  'Fraunces', 'Georgia', serif;
                --font-body:     'Inter', system-ui, sans-serif;

                /* --- shape / motion --- */
                --radius-sm:     6px;
                --radius:        14px;
                --radius-lg:     22px;
                --ease:          cubic-bezier(.22,1,.36,1);

                --container:     1180px;
              }

              @media (prefers-reduced-motion: reduce){
                *{ animation-duration: .001ms !important; animation-iteration-count: 1 !important; transition-duration: .001ms !important; }
              }

              /* =========================================================
                 2. RESET & BASE
              ========================================================= */
              *,*::before,*::after{ box-sizing: border-box; }
              html{ scroll-behavior: smooth; }
              body{
                margin:0;
                background: var(--bg);
                color: var(--text);
                font-family: var(--font-body);
                font-size: 16px;
                line-height: 1.65;
                -webkit-font-smoothing: antialiased;
              }
              img{ max-width:100%; display:block; }
              a{ color: inherit; text-decoration: none; }
              ul{ list-style: none; margin:0; padding:0; }
              h1,h2,h3,h4{ margin:0; font-family: var(--font-display); font-weight:600; line-height:1.12; }
              p{ margin:0; }
              button{ font-family: inherit; cursor:pointer; }
              :focus-visible{ outline: 2px solid var(--brass-light); outline-offset: 3px; }

              .container{ width:100%; max-width: var(--container); margin:0 auto; padding: 0 24px; }
              .section{ padding: 100px 0; }
              .section-alt{ background: var(--bg-alt); }

              /* small-caps label with a suit glyph, used instead of a generic dot eyebrow */
              .label{
                display:inline-flex; align-items:center; gap:9px;
                font-size: 13px; font-weight:600; letter-spacing:.05em;
                color: var(--brass-light);
                font-variant: small-caps;
              }
              .label .suit{ font-size:15px; color: var(--ruby); }

              .divider{
                display:flex; align-items:center; justify-content:center; gap:14px;
                margin: 0 0 20px;
              }
              .divider::before, .divider::after{ content:''; height:1px; width:46px; background: var(--border); }
              .divider .suit{ color: var(--brass); font-size:15px; }

              .section-head{ max-width: 600px; margin: 0 0 52px; }
              .section-head h2{ font-size: clamp(28px,3.6vw,40px); margin-top:12px; }
              .section-head p{ margin-top:14px; color: var(--text-muted); font-size: 16.5px; }
              .section-head.center{ margin-left:auto; margin-right:auto; text-align:center; }

              .btn{
                display:inline-flex; align-items:center; justify-content:center; gap:8px;
                padding: 14px 30px; border-radius: 3px; border: 1px solid transparent;
                font-weight:600; font-size:14.5px; letter-spacing:.03em;
                transition: transform .2s var(--ease), box-shadow .2s var(--ease), background .2s var(--ease), border-color .2s var(--ease);
                white-space:nowrap; font-family: var(--font-body);
              }
              .btn-primary{ background: linear-gradient(135deg, var(--brass-light), var(--brass)); color:#211705; }
              .btn-primary:hover{ transform: translateY(-2px); box-shadow: 0 14px 30px rgba(205,164,63,.28); }
              .btn-ghost{ background: transparent; border-color: var(--border); color: var(--text); }
              .btn-ghost:hover{ border-color: var(--brass); color: var(--brass-light); }
              .btn-block{ width:100%; }
              .btn-sm{ padding: 10px 20px; font-size:13px; }

              /* =========================================================
                 3. HEADER
              ========================================================= */
              #site-header{
                position: sticky; top:0; z-index: 100;
                background: rgba(12,23,18,.85);
                backdrop-filter: blur(14px);
                border-bottom: 1px solid var(--border);
              }
              .nav-row{ display:flex; align-items:center; justify-content:space-between; height: 80px; gap: 24px; }
              .logo{ display:flex; align-items:center; gap:10px; font-family: var(--font-display); font-size:22px; }
              .logo-mark{
                width:36px; height:36px; border-radius: 3px;
                background: linear-gradient(135deg, var(--brass-light), var(--brass));
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); color:#20160a; font-size:17px; font-weight:700;
              }
              .logo .accent{ color: var(--brass-light); }

              .nav-links{ display:flex; align-items:center; gap: 34px; }
              .nav-links a{
                font-size:14.5px; font-weight:500; color: var(--text-muted);
                transition: color .2s var(--ease);
              }
              .nav-links a:hover{ color: var(--text); }

              .nav-actions{ display:flex; align-items:center; gap:12px; }
              .nav-toggle{
                display:none; width:42px; height:42px; border-radius: var(--radius-sm);
                background: var(--surface); border:1px solid var(--border);
                align-items:center; justify-content:center;
              }
              .nav-toggle span, .nav-toggle span::before, .nav-toggle span::after{
                content:''; display:block; width:18px; height:2px; background: var(--text); position:relative;
                transition: transform .2s var(--ease), opacity .2s var(--ease);
              }
              .nav-toggle span::before{ position:absolute; top:-6px; }
              .nav-toggle span::after{ position:absolute; top:6px; }

              @media (max-width: 880px){
                .nav-links{
                  position:absolute; top:80px; left:0; right:0;
                  flex-direction:column; align-items:flex-start; gap:0;
                  background: var(--bg-alt); border-bottom:1px solid var(--border);
                  max-height:0; overflow:hidden; transition: max-height .3s var(--ease);
                }
                .nav-links.open{ max-height: 320px; }
                .nav-links a{ width:100%; padding: 16px 24px; border-top:1px solid var(--border); }
                .nav-toggle{ display:flex; }
                body.nav-open .nav-toggle span{ transform: scaleX(0); }
                body.nav-open .nav-toggle span::before{ transform: rotate(45deg) translate(4px,5px); }
                body.nav-open .nav-toggle span::after{ transform: rotate(-45deg) translate(4px,-5px); }
                .nav-actions .btn-ghost{ display:none; }
              }

              /* =========================================================
                 10. FAQ (index preview + full page)
              ========================================================= */
              .faq-list{ max-width: 760px; margin: 0 auto; display:flex; flex-direction:column; gap:12px; }
              .faq-item{ background: var(--surface); border:1px solid var(--border); border-radius: var(--radius); overflow:hidden; }
              .faq-item summary{
                list-style:none; cursor:pointer; padding: 20px 24px; display:flex; align-items:center; justify-content:space-between;
                font-weight:600; font-size:15.5px;
              }
              .faq-item summary::-webkit-details-marker{ display:none; }
              .faq-item summary::after{
                content:'+'; font-family: var(--font-display); font-size:22px; color: var(--brass); transition: transform .2s var(--ease);
              }
              .faq-item[open] summary::after{ transform: rotate(45deg); }
              .faq-item .faq-a{ padding: 0 24px 20px; color: var(--text-muted); font-size:14.5px; }

              #page-hero{
                padding: 70px 0 56px; border-bottom:1px solid var(--border);
                background: radial-gradient(600px 320px at 80% 0%, rgba(47,143,99,.16), transparent 65%), var(--bg);
              }
              .page-hero-inner{ max-width:640px; }
              .page-hero-inner h1{ font-size: clamp(32px,4.6vw,46px); margin-top:14px; }
              .page-hero-inner p{ margin-top:16px; color: var(--text-muted); font-size:16px; }

              .faq-search{
                margin-top:28px; display:flex; align-items:center; gap:10px;
                background: var(--surface); border:1px solid var(--border); border-radius: 999px;
                padding: 13px 20px; max-width:420px; color: var(--text-muted);
              }
              .faq-search svg{ flex-shrink:0; }
              .faq-search input{
                border:none; background:transparent; outline:none; color: var(--text);
                font-family: inherit; font-size:14.5px; width:100%;
              }

              .faq-grid{ display:grid; grid-template-columns: 220px 1fr; gap:48px; align-items:start; }
              @media (max-width: 820px){ .faq-grid{ grid-template-columns: 1fr; } }
              .faq-toc{ position:sticky; top:100px; }
              .faq-toc h4{ font-size:12.5px; text-transform:uppercase; letter-spacing:.08em; color: var(--text-muted); margin-bottom:16px; }
              .faq-toc ul{ display:flex; flex-direction:column; gap:4px; }
              .faq-toc a{
                display:block; padding: 9px 12px; border-radius: var(--radius-sm); font-size:14px;
                color: var(--text-muted); border-left: 2px solid transparent;
              }
              .faq-toc a:hover, .faq-toc a.active{ color: var(--brass-light); border-left-color: var(--brass); background: var(--surface); }
              .faq-category{ margin-bottom:40px; }
              .faq-category h3{ font-size:20px; margin-bottom:16px; color: var(--brass-light); font-weight:600; }
              .faq-contact{
                margin-top: 12px; padding: 28px; border:1px solid var(--border); border-radius: var(--radius);
                background: var(--surface); display:flex; align-items:center; justify-content:space-between; gap:20px; flex-wrap:wrap;
              }
              .faq-contact strong{ font-family: var(--font-display); font-size:18px; }
              .faq-contact p{ margin-top:6px; color: var(--text-muted); font-size:14.5px; }

              /* =========================================================
                 12. FOOTER
              ========================================================= */
              #site-footer{ padding: 72px 0 28px; background: var(--bg); }
              .footer-grid{ display:grid; grid-template-columns: 1.4fr 1fr 1fr 1.2fr; gap: 40px; }
              @media (max-width: 860px){ .footer-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 520px){ .footer-grid{ grid-template-columns: 1fr; } }
              .footer-grid h4{ font-family: var(--font-body); font-size:13px; text-transform:uppercase; letter-spacing:.08em; color: var(--text-muted); margin-bottom:16px; }
              .footer-grid ul{ display:flex; flex-direction:column; gap:10px; }
              .footer-grid a{ font-size:14.5px; color: var(--text-muted); transition: color .2s var(--ease); }
              .footer-grid a:hover{ color: var(--brass-light); }
              .footer-brand p{ margin-top:14px; color: var(--text-muted); font-size:14px; max-width:280px; }
              .social-row{ display:flex; gap:10px; margin-top:20px; }
              .social-row a{
                width:38px; height:38px; border-radius:50%; border:1px solid var(--border);
                display:flex; align-items:center; justify-content:center; color: var(--text);
              }
              .social-row a:hover{ border-color: var(--brass); color: var(--brass-light); }
              .payment-row{ display:flex; flex-wrap:wrap; gap:8px; }
              .payment-row span{
                padding: 7px 12px; border-radius: var(--radius-sm); background: var(--surface); border:1px solid var(--border);
                font-size:12.5px; color: var(--text-muted); font-weight:600;
              }
              .footer-bottom{
                margin-top: 56px; padding-top: 24px; border-top:1px solid var(--border);
                display:flex; justify-content:space-between; gap:20px; flex-wrap:wrap;
                font-size:12.5px; color: var(--text-muted);
              }
              .footer-bottom .age-badge{
                display:inline-flex; align-items:center; justify-content:center;
                width:26px; height:26px; border-radius:6px; border:1px solid var(--border); margin-right:8px;
                font-weight:700; color: var(--brass-light);
              }
            """;
}