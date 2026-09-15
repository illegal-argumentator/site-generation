package com.elias.site_generation.adapter.theme.out.prompt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CasinoThemePromptPolicy {

    public static final String CASINO_STYLES_TEMPLATE = """
            You are a senior UI designer and CSS architect.
            
            Your task is to redesign the appearance of an existing website by modifying ONLY
            the values of the existing CSS declarations.
            
            Rules
            
            - Preserve the CSS structure exactly.
            - Preserve every selector exactly.
            - Preserve selector order.
            - Preserve nested rules.
            - Preserve media queries.
            - Preserve pseudo classes and pseudo elements.
            - Preserve keyframes.
            - Preserve comments whenever possible.
            
            Do NOT:
            
            - add new selectors
            - remove selectors
            - rename selectors
            - merge selectors
            - split selectors
            - reorder selectors
            - remove CSS properties unless absolutely required
            - generate HTML
            - generate explanations
            
            You MAY modify only property values, including:
            
            - colors
            - gradients
            - typography
            - spacing
            - sizing
            - shadows
            - borders
            - border-radius
            - opacity
            - transitions
            - transforms
            - filters
            - backgrounds
            - hover styles
            - animations
            - responsive values
            
            Client request
            
            Below is a request from the client describing the website and/or the desired look.
            If it contains any preference relevant to visual style — theme, mood, color palette,
            specific colors, typography, brand feel, level of boldness/minimalism, or any other
            styling instruction — follow it as the primary direction for the redesign, and only
            randomize the aspects the client did NOT specify. If the client request contains no
            styling-relevant information at all (or is empty), ignore it and generate a
            completely new random visual direction as usual.
            
            Client request:
            
            %s
            
            Generate a completely new random visual direction for everything not covered by
            the client request above.
            
            Randomize (unless constrained by the client request):
            
            - color palette
            - typography
            - corner radius
            - shadows
            - gradients
            - glassmorphism level
            - neumorphism level
            - spacing scale
            - button appearance
            - cards
            - navigation
            - hero appearance
            - forms
            - footer
            - visual density
            
            The result must remain:
            
            - modern
            - coherent
            - responsive
            - visually balanced
            - professional
            
            Do not copy existing brands.
            
            Return ONLY CSS.
            
            The output must have the exact same structure as the input CSS.
            
            Only property values may change.
            
            Here are styles:
            
            %s
            
            The stylesheet must be ready to insert directly inside:
            
            <style>
                [GENERATED CSS]
            </style>
            
            in the `<head>` of `index.html`
            """;

    public static final String LUCKY_CASINO_HOME_PAGE_STYLES_SAMPLE = """
            /* =========================================================
                 1. DESIGN TOKENS — edit these to re-skin the whole page
              ========================================================= */
              :root{
                /* --- colors --- */
                --bg:            #0a0a10;
                --bg-alt:        #111017;
                --surface:       #17161f;
                --surface-2:     #1e1c28;
                --border:        #2a2836;
                --text:          #f4f2ec;
                --text-muted:    #9a97ac;
                --gold:          #f5b942;
                --gold-dark:     #c68f1f;
                --pink:          #ff3e6c;
                --purple:        #7c3aed;
                --success:       #38d996;
            
                /* --- typography --- */
                --font-display:  'Anton', 'Arial Narrow', sans-serif;
                --font-body:     'Manrope', system-ui, sans-serif;
            
                /* --- shape / motion --- */
                --radius-sm:     8px;
                --radius:        16px;
                --radius-lg:     28px;
                --ease:          cubic-bezier(.22,1,.36,1);
                --glow-gold:     0 0 60px rgba(245,185,66,.25);
                --glow-pink:     0 0 60px rgba(255,62,108,.22);
            
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
                line-height: 1.6;
                -webkit-font-smoothing: antialiased;
              }
              img{ max-width:100%; display:block; }
              a{ color: inherit; text-decoration: none; }
              ul{ list-style: none; margin:0; padding:0; }
              h1,h2,h3,h4{ margin:0; font-family: var(--font-display); font-weight:400; letter-spacing: .02em; line-height:1.05; }
              p{ margin:0; }
              button{ font-family: inherit; cursor:pointer; }
              :focus-visible{ outline: 2px solid var(--gold); outline-offset: 3px; }
            
              .container{ width:100%; max-width: var(--container); margin:0 auto; padding: 0 24px; }
              .section{ padding: 96px 0; }
              .section-alt{ background: var(--bg-alt); }
            
              .eyebrow{
                display:inline-flex; align-items:center; gap:8px;
                font-size: 12.5px; font-weight:700; letter-spacing:.16em; text-transform:uppercase;
                color: var(--gold);
              }
              .eyebrow::before{ content:''; width:8px; height:8px; border-radius:50%; background: var(--pink); box-shadow: 0 0 12px var(--pink); }
            
              .section-head{ max-width: 620px; margin: 0 0 48px; }
              .section-head h2{ font-size: clamp(28px,4vw,42px); margin-top:14px; text-transform: uppercase; }
              .section-head p{ margin-top:14px; color: var(--text-muted); font-size: 16.5px; }
              .section-head.center{ margin-left:auto; margin-right:auto; text-align:center; }
            
              .btn{
                display:inline-flex; align-items:center; justify-content:center; gap:8px;
                padding: 14px 28px; border-radius: 999px; border: 1px solid transparent;
                font-weight:700; font-size:14.5px; letter-spacing:.03em; text-transform:uppercase;
                transition: transform .2s var(--ease), box-shadow .2s var(--ease), background .2s var(--ease), border-color .2s var(--ease);
                white-space:nowrap;
              }
              .btn-primary{ background: linear-gradient(135deg, var(--gold), var(--gold-dark)); color:#1a1305; box-shadow: var(--glow-gold); }
              .btn-primary:hover{ transform: translateY(-2px); box-shadow: 0 0 70px rgba(245,185,66,.4); }
              .btn-ghost{ background: transparent; border-color: var(--border); color: var(--text); }
              .btn-ghost:hover{ border-color: var(--gold); color: var(--gold); }
              .btn-block{ width:100%; }
              .btn-sm{ padding: 10px 18px; font-size:13px; }
            
              /* =========================================================
                 3. HEADER
              ========================================================= */
              #site-header{
                position: sticky; top:0; z-index: 100;
                background: rgba(10,10,16,.75);
                backdrop-filter: blur(14px);
                border-bottom: 1px solid var(--border);
              }
              .nav-row{ display:flex; align-items:center; justify-content:space-between; height: 78px; gap: 24px; }
              .logo{ display:flex; align-items:center; gap:10px; font-family: var(--font-display); font-size:22px; letter-spacing:.03em; text-transform:uppercase; }
              .logo-mark{
                width:34px; height:34px; border-radius:10px;
                background: linear-gradient(135deg, var(--gold), var(--pink));
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); color:#160b06; font-size:18px;
              }
              .logo .accent{ color: var(--gold); }
            
              .nav-links{ display:flex; align-items:center; gap: 34px; }
              .nav-links a{
                font-size:14.5px; font-weight:600; color: var(--text-muted);
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
                  position:absolute; top:78px; left:0; right:0;
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
                 4. HERO + signature slot reel
              ========================================================= */
              #hero{
                position:relative; overflow:hidden;
                padding: 88px 0 100px;
                background:
                  radial-gradient(680px 420px at 82% 15%, rgba(124,58,237,.28), transparent 65%),
                  radial-gradient(600px 500px at 12% 100%, rgba(245,185,66,.14), transparent 60%),
                  var(--bg);
              }
              .hero-grid{ display:grid; grid-template-columns: 1.1fr .9fr; gap: 56px; align-items:center; }
              @media (max-width: 900px){ .hero-grid{ grid-template-columns: 1fr; } }
            
              .hero-copy h1{
                font-size: clamp(40px, 6vw, 68px);
                text-transform: uppercase;
                margin-top: 18px;
                background: linear-gradient(100deg, var(--text) 40%, var(--gold) 70%, var(--pink) 100%);
                -webkit-background-clip: text; background-clip:text; color: transparent;
              }
              .hero-copy p{ margin-top: 22px; font-size: 18px; color: var(--text-muted); max-width: 480px; }
              .hero-actions{ margin-top: 34px; display:flex; gap:16px; flex-wrap:wrap; }
              .hero-meta{ margin-top:40px; display:flex; gap:28px; flex-wrap:wrap; }
              .hero-meta div{ display:flex; flex-direction:column; }
              .hero-meta strong{ font-family: var(--font-display); font-size:24px; color: var(--gold); }
              .hero-meta span{ font-size:12.5px; color: var(--text-muted); text-transform:uppercase; letter-spacing:.08em; }
            
              /* --- slot machine signature element --- */
              .slot-machine{
                position:relative; padding: 26px;
                background: linear-gradient(180deg, var(--surface-2), var(--surface));
                border: 1px solid var(--border); border-radius: var(--radius-lg);
                box-shadow: var(--glow-gold), inset 0 1px 0 rgba(255,255,255,.04);
              }
              .slot-machine::before{
                content:'JACKPOT'; position:absolute; top:-14px; left:50%; transform:translateX(-50%);
                background: linear-gradient(135deg, var(--gold), var(--pink));
                color:#160b06; font-family: var(--font-display); font-size:13px; letter-spacing:.12em;
                padding: 6px 18px; border-radius: 999px;
              }
              .reels{
                display:grid; grid-template-columns: repeat(3,1fr); gap:12px;
                background: var(--bg); border-radius: var(--radius); padding: 16px;
                border: 1px solid var(--border);
              }
              .reel{
                height: 220px; overflow:hidden; border-radius: var(--radius-sm);
                background: var(--surface);
                position:relative;
                mask-image: linear-gradient(180deg, transparent, #000 20%, #000 80%, transparent);
              }
              .reel-strip{
                display:flex; flex-direction:column; align-items:center;
                animation: spin 3.2s linear infinite;
              }
              .reel:nth-child(2) .reel-strip{ animation-duration: 3.9s; animation-delay:.15s; }
              .reel:nth-child(3) .reel-strip{ animation-duration: 4.6s; animation-delay:.3s; }
              .reel-strip span{ height: 110px; display:flex; align-items:center; justify-content:center; font-size: 52px; }
              @keyframes spin{ from{ transform: translateY(0); } to{ transform: translateY(-50%); } }
            
              .slot-lever{
                position:absolute; right:-16px; top:50%; transform:translateY(-50%);
                width:14px; height:120px; border-radius:999px;
                background: linear-gradient(180deg, var(--pink), var(--gold-dark));
                box-shadow: 0 0 24px rgba(255,62,108,.5);
              }
              .slot-lever::after{
                content:''; position:absolute; top:-14px; left:50%; transform:translateX(-50%);
                width:30px; height:30px; border-radius:50%;
                background: radial-gradient(circle at 35% 30%, #fff, var(--pink) 60%);
              }
              .slot-payline{ margin-top:16px; display:flex; align-items:center; justify-content:space-between; gap:12px; }
              .slot-payline .win{ font-family: var(--font-display); color: var(--gold); font-size: 15px; letter-spacing:.05em; }
              .slot-payline .win .amt{ color:#fff; }
              .pulse-dot{ width:8px; height:8px; border-radius:50%; background: var(--success); box-shadow: 0 0 10px var(--success); animation: pulse 1.6s ease-in-out infinite; }
              @keyframes pulse{ 0%,100%{ opacity:1; } 50%{ opacity:.35; } }
            
              /* =========================================================
                 5. STATS STRIP
              ========================================================= */
              #stats{ padding: 56px 0; background: var(--bg-alt); border-top:1px solid var(--border); border-bottom:1px solid var(--border); }
              .stats-grid{ display:grid; grid-template-columns: repeat(4,1fr); gap: 24px; text-align:center; }
              @media (max-width: 760px){ .stats-grid{ grid-template-columns: repeat(2,1fr); } }
              .stat h3{ font-size: clamp(26px,3.4vw,36px); color: var(--gold); }
              .stat span{ display:block; margin-top:8px; font-size:13px; color: var(--text-muted); text-transform:uppercase; letter-spacing:.08em; }
            
              /* =========================================================
                 6. FEATURES
              ========================================================= */
              .features-grid{ display:grid; grid-template-columns: repeat(4,1fr); gap:22px; }
              @media (max-width: 980px){ .features-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 560px){ .features-grid{ grid-template-columns: 1fr; } }
              .feature-card{
                background: var(--surface); border:1px solid var(--border); border-radius: var(--radius);
                padding: 28px 24px; transition: transform .25s var(--ease), border-color .25s var(--ease);
              }
              .feature-card:hover{ transform: translateY(-4px); border-color: var(--gold); }
              .feature-icon{
                width:46px; height:46px; border-radius: 12px; margin-bottom:18px;
                display:flex; align-items:center; justify-content:center;
                background: linear-gradient(135deg, rgba(245,185,66,.18), rgba(255,62,108,.14));
                color: var(--gold);
              }
              .feature-card h3{ font-size:17px; text-transform: uppercase; letter-spacing:.02em; font-family: var(--font-body); font-weight:800; }
              .feature-card p{ margin-top:10px; font-size:14.5px; color: var(--text-muted); }
            
              /* =========================================================
                 7. GAMES GRID
              ========================================================= */
              .games-grid{ display:grid; grid-template-columns: repeat(3,1fr); gap:22px; }
              @media (max-width: 980px){ .games-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 620px){ .games-grid{ grid-template-columns: 1fr; } }
              .game-card{
                background: var(--surface); border:1px solid var(--border); border-radius: var(--radius);
                overflow:hidden; transition: transform .25s var(--ease), box-shadow .25s var(--ease);
              }
              .game-card:hover{ transform: translateY(-4px); box-shadow: 0 16px 40px rgba(0,0,0,.35); }
              .game-thumb{
                height: 150px; display:flex; align-items:center; justify-content:center; font-size:48px;
                position:relative;
              }
              .game-thumb .tag{
                position:absolute; top:12px; left:12px; font-size:11px; font-weight:800; letter-spacing:.06em;
                text-transform:uppercase; background: rgba(0,0,0,.4); backdrop-filter: blur(4px);
                padding: 5px 10px; border-radius: 999px; color:#fff;
              }
              .g1{ background: linear-gradient(135deg,#3a1c71,#7c3aed); }
              .g2{ background: linear-gradient(135deg,#8a2b0e,#f5b942); }
              .g3{ background: linear-gradient(135deg,#0f2a3d,#38d996); }
              .g4{ background: linear-gradient(135deg,#5c0f2a,#ff3e6c); }
              .g5{ background: linear-gradient(135deg,#1a1a2e,#7c3aed); }
              .g6{ background: linear-gradient(135deg,#3d2a0f,#f5b942); }
              .game-body{ padding: 18px 20px 20px; }
              .game-body h3{ font-size:16px; font-family: var(--font-body); font-weight:800; text-transform:none; }
              .game-meta{ display:flex; justify-content:space-between; margin-top:8px; font-size:12.5px; color: var(--text-muted); }
              .game-body .btn{ margin-top:16px; width:100%; }
            
              /* =========================================================
                 8. JACKPOT BANNER
              ========================================================= */
              #jackpot{
                margin: 0 24px; max-width: calc(var(--container) - 0px); margin-left:auto; margin-right:auto;
                border-radius: var(--radius-lg);
                background: linear-gradient(120deg, #241132, #3a1440 45%, #241132);
                border: 1px solid rgba(245,185,66,.35);
                padding: 56px 48px;
                display:flex; align-items:center; justify-content:space-between; gap:32px; flex-wrap:wrap;
                position:relative; overflow:hidden;
              }
              #jackpot::before{
                content:''; position:absolute; inset:0;
                background: radial-gradient(420px 240px at 85% 0%, rgba(245,185,66,.25), transparent 70%);
              }
              .jackpot-copy{ position:relative; z-index:1; max-width: 460px; }
              .jackpot-copy .eyebrow{ color:#fff; }
              .jackpot-copy .eyebrow::before{ background: var(--gold); box-shadow: 0 0 12px var(--gold); }
              .jackpot-amount{
                font-family: var(--font-display); font-size: clamp(40px,6vw,64px); margin-top:12px;
                color: var(--gold); text-shadow: 0 0 40px rgba(245,185,66,.5);
              }
              .jackpot-copy p{ margin-top:10px; color: rgba(244,242,236,.8); }
              .countdown{ position:relative; z-index:1; display:flex; gap:14px; }
              .countdown .box{
                width:78px; padding: 14px 0; text-align:center; border-radius: var(--radius-sm);
                background: rgba(0,0,0,.3); border:1px solid rgba(255,255,255,.1);
              }
              .countdown .box strong{ display:block; font-family: var(--font-display); font-size:28px; color:#fff; }
              .countdown .box span{ font-size:11px; color: var(--text-muted); text-transform:uppercase; letter-spacing:.06em; }
            
              /* =========================================================
                 9. FAQ
              ========================================================= */
              .faq-list{ max-width: 760px; margin: 0 auto; display:flex; flex-direction:column; gap:12px; }
              .faq-item{ background: var(--surface); border:1px solid var(--border); border-radius: var(--radius); overflow:hidden; }
              .faq-item summary{
                list-style:none; cursor:pointer; padding: 20px 24px; display:flex; align-items:center; justify-content:space-between;
                font-weight:700; font-size:15.5px;
              }
              .faq-item summary::-webkit-details-marker{ display:none; }
              .faq-item summary::after{
                content:'+'; font-family: var(--font-display); font-size:22px; color: var(--gold); transition: transform .2s var(--ease);
              }
              .faq-item[open] summary::after{ transform: rotate(45deg); }
              .faq-item .faq-a{ padding: 0 24px 20px; color: var(--text-muted); font-size:14.5px; }
            
              /* =========================================================
                 10. CTA / SIGNUP BAND
              ========================================================= */
              #cta{
                text-align:center; padding: 90px 0;
                background:
                  radial-gradient(600px 300px at 50% 0%, rgba(255,62,108,.16), transparent 70%),
                  var(--bg-alt);
                border-top: 1px solid var(--border);
              }
              #cta h2{ font-size: clamp(28px,4.4vw,44px); text-transform:uppercase; }
              #cta p{ margin-top:14px; color: var(--text-muted); max-width:480px; margin-left:auto; margin-right:auto; }
              .cta-form{ margin-top: 30px; display:flex; gap:10px; max-width: 420px; margin-left:auto; margin-right:auto; }
              .cta-form input{
                flex:1; padding: 14px 18px; border-radius: 999px; border:1px solid var(--border);
                background: var(--surface); color: var(--text); font-family: inherit; font-size:14.5px;
              }
              .cta-form input:focus{ border-color: var(--gold); }
              @media (max-width: 480px){ .cta-form{ flex-direction:column; } }
            
              /* =========================================================
                 11. FOOTER
              ========================================================= */
              #site-footer{ padding: 72px 0 28px; background: var(--bg); }
              .footer-grid{ display:grid; grid-template-columns: 1.4fr 1fr 1fr 1.2fr; gap: 40px; }
              @media (max-width: 860px){ .footer-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 520px){ .footer-grid{ grid-template-columns: 1fr; } }
              .footer-grid h4{ font-family: var(--font-body); font-size:13px; text-transform:uppercase; letter-spacing:.08em; color: var(--text-muted); margin-bottom:16px; }
              .footer-grid ul{ display:flex; flex-direction:column; gap:10px; }
              .footer-grid a{ font-size:14.5px; color: var(--text-muted); transition: color .2s var(--ease); }
              .footer-grid a:hover{ color: var(--gold); }
              .footer-brand p{ margin-top:14px; color: var(--text-muted); font-size:14px; max-width:280px; }
              .social-row{ display:flex; gap:10px; margin-top:20px; }
              .social-row a{
                width:38px; height:38px; border-radius:50%; border:1px solid var(--border);
                display:flex; align-items:center; justify-content:center; color: var(--text);
              }
              .social-row a:hover{ border-color: var(--gold); color: var(--gold); }
              .payment-row{ display:flex; flex-wrap:wrap; gap:8px; }
              .payment-row span{
                padding: 7px 12px; border-radius: var(--radius-sm); background: var(--surface); border:1px solid var(--border);
                font-size:12.5px; color: var(--text-muted); font-weight:700;
              }
              .footer-bottom{
                margin-top: 56px; padding-top: 24px; border-top:1px solid var(--border);
                display:flex; justify-content:space-between; gap:20px; flex-wrap:wrap;
                font-size:12.5px; color: var(--text-muted);
              }
              .footer-bottom .age-badge{
                display:inline-flex; align-items:center; justify-content:center;
                width:26px; height:26px; border-radius:6px; border:1px solid var(--border); margin-right:8px;
                font-weight:800; color: var(--gold);
              }
            """;

    public static final String LUCKY_CASINO_COOKIES_PAGE_STYLES_SAMPLE = """
            /* =========================================================
                 1. DESIGN TOKENS — edit these to re-skin the whole page
              ========================================================= */
              :root{
                /* --- colors --- */
                --bg:            #030405;
                --bg-alt:        #08090b;
                --surface:       #111318;
                --surface-2:     #191c22;
                --border:        #2a2f38;
                --text:          #f5f7fa;
                --text-muted:    #8c95a3;
                --gold:          #8af7ff;
                --gold-dark:     #2fc8dd;
                --pink:          #b88cff;
                --purple:        #4f7cff;
                --success:       #45ff9a;
            
                /* --- typography --- */
                --font-display:  'Bebas Neue', 'Arial Narrow', sans-serif;
                --font-body:     'Inter', system-ui, sans-serif;
            
                /* --- shape / motion --- */
                --radius-sm:     10px;
                --radius:        18px;
                --radius-lg:     30px;
                --ease:          cubic-bezier(.16,1,.3,1);
                --glow-gold:     0 0 58px rgba(138,247,255,.18);
                --glow-pink:     0 0 58px rgba(184,140,255,.16);
            
                --container:     1160px;
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
              h1,h2,h3,h4{ margin:0; font-family: var(--font-display); font-weight:400; letter-spacing: .045em; line-height:1; }
              p{ margin:0; }
              button{ font-family: inherit; cursor:pointer; }
              :focus-visible{ outline: 2px solid var(--gold); outline-offset: 4px; }
            
              .container{ width:100%; max-width: var(--container); margin:0 auto; padding: 0 28px; }
              .section{ padding: 104px 0; }
              .section-alt{ background: var(--bg-alt); }
            
              .eyebrow{
                display:inline-flex; align-items:center; gap:10px;
                font-size: 12px; font-weight:800; letter-spacing:.18em; text-transform:uppercase;
                color: var(--gold);
              }
              .eyebrow::before{ content:''; width:7px; height:7px; border-radius:50%; background: var(--pink); box-shadow: 0 0 16px var(--pink); }
            
              .section-head{ max-width: 650px; margin: 0 0 52px; }
              .section-head h2{ font-size: clamp(30px,4vw,46px); margin-top:16px; text-transform: uppercase; }
              .section-head p{ margin-top:16px; color: var(--text-muted); font-size: 16px; }
              .section-head.center{ margin-left:auto; margin-right:auto; text-align:center; }
            
              .btn{
                display:inline-flex; align-items:center; justify-content:center; gap:9px;
                padding: 14px 30px; border-radius: 14px; border: 1px solid transparent;
                font-weight:800; font-size:13px; letter-spacing:.08em; text-transform:uppercase;
                transition: transform .22s var(--ease), box-shadow .22s var(--ease), background .22s var(--ease), border-color .22s var(--ease);
                white-space:nowrap;
              }
              .btn-primary{ background: linear-gradient(135deg, var(--gold), var(--gold-dark)); color:#021114; box-shadow: var(--glow-gold); }
              .btn-primary:hover{ transform: translateY(-3px); box-shadow: 0 0 76px rgba(138,247,255,.32); }
              .btn-ghost{ background: rgba(255,255,255,.025); border-color: var(--border); color: var(--text); }
              .btn-ghost:hover{ border-color: var(--gold); color: var(--gold); }
              .btn-block{ width:100%; }
              .btn-sm{ padding: 10px 20px; font-size:12px; }
            
              /* =========================================================
                 3. HEADER
              ========================================================= */
              #site-header{
                position: sticky; top:0; z-index: 100;
                background: rgba(3,4,5,.82);
                backdrop-filter: blur(18px);
                border-bottom: 1px solid var(--border);
              }
              .nav-row{ display:flex; align-items:center; justify-content:space-between; height: 76px; gap: 26px; }
              .logo{ display:flex; align-items:center; gap:12px; font-family: var(--font-display); font-size:24px; letter-spacing:.055em; text-transform:uppercase; }
              .logo-mark{
                width:36px; height:36px; border-radius:12px;
                background: linear-gradient(135deg, var(--gold), var(--purple));
                display:flex; align-items:center; justify-content:center;
                font-family: var(--font-display); color:#020506; font-size:18px;
              }
              .logo .accent{ color: var(--gold); }
            
              .nav-links{ display:flex; align-items:center; gap: 36px; }
              .nav-links a{
                font-size:13.5px; font-weight:700; color: var(--text-muted);
                transition: color .22s var(--ease);
              }
              .nav-links a:hover{ color: var(--text); }
            
              .nav-actions{ display:flex; align-items:center; gap:12px; }
              .nav-toggle{
                display:none; width:44px; height:44px; border-radius: var(--radius-sm);
                background: var(--surface); border:1px solid var(--border);
                align-items:center; justify-content:center;
              }
              .nav-toggle span, .nav-toggle span::before, .nav-toggle span::after{
                content:''; display:block; width:18px; height:2px; background: var(--text); position:relative;
                transition: transform .22s var(--ease), opacity .22s var(--ease);
              }
              .nav-toggle span::before{ position:absolute; top:-6px; }
              .nav-toggle span::after{ position:absolute; top:6px; }
            
              @media (max-width: 880px){
                .nav-links{
                  position:absolute; top:76px; left:0; right:0;
                  flex-direction:column; align-items:flex-start; gap:0;
                  background: var(--bg-alt); border-bottom:1px solid var(--border);
                  max-height:0; overflow:hidden; transition: max-height .32s var(--ease);
                }
                .nav-links.open{ max-height: 340px; }
                .nav-links a{ width:100%; padding: 17px 28px; border-top:1px solid var(--border); }
                .nav-toggle{ display:flex; }
                body.nav-open .nav-toggle span{ transform: scaleX(0); }
                body.nav-open .nav-toggle span::before{ transform: rotate(45deg) translate(4px,5px); }
                body.nav-open .nav-toggle span::after{ transform: rotate(-45deg) translate(4px,-5px); }
                .nav-actions .btn-ghost{ display:none; }
              }
            
              /* =========================================================
                 4. HERO + signature slot reel
              ========================================================= */
              #hero{
                position:relative; overflow:hidden;
                padding: 96px 0 108px;
                background:
                  radial-gradient(720px 440px at 82% 12%, rgba(79,124,255,.20), transparent 66%),
                  radial-gradient(620px 520px at 8% 100%, rgba(138,247,255,.11), transparent 62%),
                  var(--bg);
              }
              .hero-grid{ display:grid; grid-template-columns: 1.08fr .92fr; gap: 60px; align-items:center; }
              @media (max-width: 900px){ .hero-grid{ grid-template-columns: 1fr; } }
            
              .hero-copy h1{
                font-size: clamp(44px, 6.4vw, 74px);
                text-transform: uppercase;
                margin-top: 20px;
                background: linear-gradient(100deg, var(--text) 32%, var(--gold) 70%, var(--pink) 100%);
                -webkit-background-clip: text; background-clip:text; color: transparent;
              }
              .hero-copy p{ margin-top: 24px; font-size: 18px; color: var(--text-muted); max-width: 500px; }
              .hero-actions{ margin-top: 36px; display:flex; gap:14px; flex-wrap:wrap; }
              .hero-meta{ margin-top:42px; display:flex; gap:32px; flex-wrap:wrap; }
              .hero-meta div{ display:flex; flex-direction:column; }
              .hero-meta strong{ font-family: var(--font-display); font-size:26px; color: var(--gold); }
              .hero-meta span{ font-size:12px; color: var(--text-muted); text-transform:uppercase; letter-spacing:.1em; }
            
              /* --- slot machine signature element --- */
              .slot-machine{
                position:relative; padding: 28px;
                background: linear-gradient(180deg, var(--surface-2), var(--surface));
                border: 1px solid var(--border); border-radius: var(--radius-lg);
                box-shadow: var(--glow-gold), inset 0 1px 0 rgba(255,255,255,.055);
              }
              .slot-machine::before{
                content:'JACKPOT'; position:absolute; top:-15px; left:50%; transform:translateX(-50%);
                background: linear-gradient(135deg, var(--gold), var(--purple));
                color:#020506; font-family: var(--font-display); font-size:13px; letter-spacing:.16em;
                padding: 7px 20px; border-radius: 999px;
              }
              .reels{
                display:grid; grid-template-columns: repeat(3,1fr); gap:12px;
                background: var(--bg); border-radius: var(--radius); padding: 16px;
                border: 1px solid var(--border);
              }
              .reel{
                height: 224px; overflow:hidden; border-radius: var(--radius-sm);
                background: var(--surface);
                position:relative;
                mask-image: linear-gradient(180deg, transparent, #000 18%, #000 82%, transparent);
              }
              .reel-strip{
                display:flex; flex-direction:column; align-items:center;
                animation: spin 3.4s linear infinite;
              }
              .reel:nth-child(2) .reel-strip{ animation-duration: 4.1s; animation-delay:.18s; }
              .reel:nth-child(3) .reel-strip{ animation-duration: 4.8s; animation-delay:.34s; }
              .reel-strip span{ height: 112px; display:flex; align-items:center; justify-content:center; font-size: 54px; }
              @keyframes spin{ from{ transform: translateY(0); } to{ transform: translateY(-50%); } }
            
              .slot-lever{
                position:absolute; right:-17px; top:50%; transform:translateY(-50%);
                width:14px; height:124px; border-radius:999px;
                background: linear-gradient(180deg, var(--pink), var(--gold-dark));
                box-shadow: 0 0 28px rgba(184,140,255,.42);
              }
              .slot-lever::after{
                content:''; position:absolute; top:-15px; left:50%; transform:translateX(-50%);
                width:32px; height:32px; border-radius:50%;
                background: radial-gradient(circle at 35% 30%, #fff, var(--pink) 62%);
              }
              .slot-payline{ margin-top:18px; display:flex; align-items:center; justify-content:space-between; gap:14px; }
              .slot-payline .win{ font-family: var(--font-display); color: var(--gold); font-size: 16px; letter-spacing:.08em; }
              .slot-payline .win .amt{ color:#fff; }
              .pulse-dot{ width:8px; height:8px; border-radius:50%; background: var(--success); box-shadow: 0 0 14px var(--success); animation: pulse 1.7s ease-in-out infinite; }
              @keyframes pulse{ 0%,100%{ opacity:1; } 50%{ opacity:.3; } }
            
              /* =========================================================
                 5. STATS STRIP
              ========================================================= */
              #stats{ padding: 58px 0; background: var(--bg-alt); border-top:1px solid var(--border); border-bottom:1px solid var(--border); }
              .stats-grid{ display:grid; grid-template-columns: repeat(4,1fr); gap: 26px; text-align:center; }
              @media (max-width: 760px){ .stats-grid{ grid-template-columns: repeat(2,1fr); } }
              .stat h3{ font-size: clamp(28px,3.5vw,38px); color: var(--gold); }
              .stat span{ display:block; margin-top:9px; font-size:12px; color: var(--text-muted); text-transform:uppercase; letter-spacing:.1em; }
            
              /* =========================================================
                 6. FEATURES
              ========================================================= */
              .features-grid{ display:grid; grid-template-columns: repeat(4,1fr); gap:24px; }
              @media (max-width: 980px){ .features-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 560px){ .features-grid{ grid-template-columns: 1fr; } }
              .feature-card{
                background: var(--surface); border:1px solid var(--border); border-radius: var(--radius);
                padding: 30px 26px; transition: transform .26s var(--ease), border-color .26s var(--ease);
              }
              .feature-card:hover{ transform: translateY(-5px); border-color: var(--gold); }
              .feature-icon{
                width:48px; height:48px; border-radius: 14px; margin-bottom:20px;
                display:flex; align-items:center; justify-content:center;
                background: linear-gradient(135deg, rgba(138,247,255,.16), rgba(184,140,255,.12));
                color: var(--gold);
              }
              .feature-card h3{ font-size:16px; text-transform: uppercase; letter-spacing:.06em; font-family: var(--font-body); font-weight:850; }
              .feature-card p{ margin-top:12px; font-size:14px; color: var(--text-muted); }
            
              /* =========================================================
                 7. GAMES GRID
              ========================================================= */
              .games-grid{ display:grid; grid-template-columns: repeat(3,1fr); gap:24px; }
              @media (max-width: 980px){ .games-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 620px){ .games-grid{ grid-template-columns: 1fr; } }
              .game-card{
                background: var(--surface); border:1px solid var(--border); border-radius: var(--radius);
                overflow:hidden; transition: transform .26s var(--ease), box-shadow .26s var(--ease);
              }
              .game-card:hover{ transform: translateY(-5px); box-shadow: 0 20px 48px rgba(0,0,0,.48); }
              .game-thumb{
                height: 158px; display:flex; align-items:center; justify-content:center; font-size:50px;
                position:relative;
              }
              .game-thumb .tag{
                position:absolute; top:13px; left:13px; font-size:10.5px; font-weight:850; letter-spacing:.08em;
                text-transform:uppercase; background: rgba(0,0,0,.48); backdrop-filter: blur(6px);
                padding: 6px 11px; border-radius: 999px; color:#fff;
              }
              .g1{ background: linear-gradient(135deg,#0b1020,#4f7cff); }
              .g2{ background: linear-gradient(135deg,#111318,#8af7ff); }
              .g3{ background: linear-gradient(135deg,#061313,#45ff9a); }
              .g4{ background: linear-gradient(135deg,#130b1f,#b88cff); }
              .g5{ background: linear-gradient(135deg,#08090b,#4f7cff); }
              .g6{ background: linear-gradient(135deg,#0b1517,#2fc8dd); }
              .game-body{ padding: 20px 22px 22px; }
              .game-body h3{ font-size:16px; font-family: var(--font-body); font-weight:850; text-transform:none; }
              .game-meta{ display:flex; justify-content:space-between; margin-top:9px; font-size:12px; color: var(--text-muted); }
              .game-body .btn{ margin-top:18px; width:100%; }
            
              /* =========================================================
                 8. JACKPOT BANNER
              ========================================================= */
              #jackpot{
                margin: 0 28px; max-width: calc(var(--container) - 0px); margin-left:auto; margin-right:auto;
                border-radius: var(--radius-lg);
                background: linear-gradient(120deg, #090b0f, #141923 45%, #090b0f);
                border: 1px solid rgba(138,247,255,.28);
                padding: 60px 52px;
                display:flex; align-items:center; justify-content:space-between; gap:34px; flex-wrap:wrap;
                position:relative; overflow:hidden;
              }
              #jackpot::before{
                content:''; position:absolute; inset:0;
                background: radial-gradient(440px 260px at 86% 0%, rgba(138,247,255,.18), transparent 72%);
              }
              .jackpot-copy{ position:relative; z-index:1; max-width: 480px; }
              .jackpot-copy .eyebrow{ color:#fff; }
              .jackpot-copy .eyebrow::before{ background: var(--gold); box-shadow: 0 0 16px var(--gold); }
              .jackpot-amount{
                font-family: var(--font-display); font-size: clamp(44px,6.2vw,68px); margin-top:14px;
                color: var(--gold); text-shadow: 0 0 42px rgba(138,247,255,.38);
              }
              .jackpot-copy p{ margin-top:12px; color: rgba(245,247,250,.76); }
              .countdown{ position:relative; z-index:1; display:flex; gap:14px; }
              .countdown .box{
                width:80px; padding: 15px 0; text-align:center; border-radius: var(--radius-sm);
                background: rgba(255,255,255,.045); border:1px solid rgba(255,255,255,.11);
              }
              .countdown .box strong{ display:block; font-family: var(--font-display); font-size:30px; color:#fff; }
              .countdown .box span{ font-size:10.5px; color: var(--text-muted); text-transform:uppercase; letter-spacing:.08em; }
            
              /* =========================================================
                 9. FAQ
              ========================================================= */
              .faq-list{ max-width: 780px; margin: 0 auto; display:flex; flex-direction:column; gap:14px; }
              .faq-item{ background: var(--surface); border:1px solid var(--border); border-radius: var(--radius); overflow:hidden; }
              .faq-item summary{
                list-style:none; cursor:pointer; padding: 22px 26px; display:flex; align-items:center; justify-content:space-between;
                font-weight:750; font-size:15px;
              }
              .faq-item summary::-webkit-details-marker{ display:none; }
              .faq-item summary::after{
                content:'+'; font-family: var(--font-display); font-size:24px; color: var(--gold); transition: transform .22s var(--ease);
              }
              .faq-item[open] summary::after{ transform: rotate(45deg); }
              .faq-item .faq-a{ padding: 0 26px 22px; color: var(--text-muted); font-size:14px; }
            
              /* =========================================================
                 10. CTA / SIGNUP BAND
              ========================================================= */
              #cta{
                text-align:center; padding: 96px 0;
                background:
                  radial-gradient(620px 320px at 50% 0%, rgba(184,140,255,.13), transparent 72%),
                  var(--bg-alt);
                border-top: 1px solid var(--border);
              }
              #cta h2{ font-size: clamp(30px,4.5vw,48px); text-transform:uppercase; }
              #cta p{ margin-top:16px; color: var(--text-muted); max-width:500px; margin-left:auto; margin-right:auto; }
              .cta-form{ margin-top: 32px; display:flex; gap:12px; max-width: 440px; margin-left:auto; margin-right:auto; }
              .cta-form input{
                flex:1; padding: 15px 18px; border-radius: 14px; border:1px solid var(--border);
                background: var(--surface); color: var(--text); font-family: inherit; font-size:14px;
              }
              .cta-form input:focus{ border-color: var(--gold); }
              @media (max-width: 480px){ .cta-form{ flex-direction:column; } }
            
              /* =========================================================
                 11. FOOTER
              ========================================================= */
              #site-footer{ padding: 76px 0 30px; background: var(--bg); }
              .footer-grid{ display:grid; grid-template-columns: 1.4fr 1fr 1fr 1.2fr; gap: 42px; }
              @media (max-width: 860px){ .footer-grid{ grid-template-columns: repeat(2,1fr); } }
              @media (max-width: 520px){ .footer-grid{ grid-template-columns: 1fr; } }
              .footer-grid h4{ font-family: var(--font-body); font-size:12px; text-transform:uppercase; letter-spacing:.1em; color: var(--text-muted); margin-bottom:18px; }
              .footer-grid ul{ display:flex; flex-direction:column; gap:11px; }
              .footer-grid a{ font-size:14px; color: var(--text-muted); transition: color .22s var(--ease); }
              .footer-grid a:hover{ color: var(--gold); }
              .footer-brand p{ margin-top:16px; color: var(--text-muted); font-size:14px; max-width:290px; }
              .social-row{ display:flex; gap:11px; margin-top:22px; }
              .social-row a{
                width:40px; height:40px; border-radius:12px; border:1px solid var(--border);
                display:flex; align-items:center; justify-content:center; color: var(--text);
              }
              .social-row a:hover{ border-color: var(--gold); color: var(--gold); }
              .payment-row{ display:flex; flex-wrap:wrap; gap:9px; }
              .payment-row span{
                padding: 8px 13px; border-radius: var(--radius-sm); background: var(--surface); border:1px solid var(--border);
                font-size:12px; color: var(--text-muted); font-weight:800;
              }
              .footer-bottom{
                margin-top: 58px; padding-top: 26px; border-top:1px solid var(--border);
                display:flex; justify-content:space-between; gap:22px; flex-wrap:wrap;
                font-size:12px; color: var(--text-muted);
              }
              .footer-bottom .age-badge{
                display:inline-flex; align-items:center; justify-content:center;
                width:28px; height:28px; border-radius:8px; border:1px solid var(--border); margin-right:9px;
                font-weight:850; color: var(--gold);
              }
            
              /* =========================================================
                 12. POLICY / LEGAL PAGE CONTENT
                 (page-hero, policy-grid, policy-toc, policy-body — the cookie
                 policy page reuses the site's header/footer CSS above but never
                 had rules of its own, which is why the text ran together)
              ========================================================= */
              #page-hero{
                position:relative; overflow:hidden;
                padding: 88px 0 72px;
                background:
                  radial-gradient(560px 360px at 88% 0%, rgba(138,247,255,.14), transparent 68%),
                  radial-gradient(480px 320px at 6% 100%, rgba(184,140,255,.10), transparent 62%),
                  var(--bg-alt);
                border-bottom: 1px solid var(--border);
              }
              .page-hero-inner{ max-width: 640px; }
              #page-hero h1{
                font-size: clamp(36px, 5vw, 52px);
                text-transform: uppercase;
                margin-top: 16px;
                color: var(--text);
              }
              #page-hero p{
                margin-top: 18px;
                font-size: 16px;
                line-height: 1.7;
                color: var(--text-muted);
                max-width: 58ch;
              }
            
              .policy-grid{ display:grid; grid-template-columns: 240px 1fr; gap: 56px; align-items:start; }
              @media (max-width: 860px){ .policy-grid{ grid-template-columns: 1fr; gap: 32px; } }
            
              .policy-toc{ position: sticky; top: 100px; }
              .policy-toc h4{
                font-family: var(--font-body); font-weight:800; font-size:12px;
                text-transform:uppercase; letter-spacing:.1em; color: var(--text-muted); margin-bottom:16px;
              }
              .policy-toc ul{ display:flex; flex-direction:column; gap:2px; border-left:1px solid var(--border); }
              .policy-toc a{
                display:block; padding: 8px 0 8px 18px; margin-left:-1px;
                font-size:13.5px; font-weight:600; color: var(--text-muted);
                border-left:1px solid transparent;
                transition: color .22s var(--ease), border-color .22s var(--ease);
              }
              .policy-toc a:hover{ color: var(--gold); border-color: var(--gold); }
              @media (max-width: 860px){
                .policy-toc{ position:static; }
                .policy-toc ul{ flex-direction:row; flex-wrap:wrap; border-left:none; gap:10px; }
                .policy-toc a{ border:1px solid var(--border); border-radius:999px; padding:7px 16px; margin:0; }
              }
            
              .policy-body{ max-width: 700px; }
              .policy-block{ padding: 32px 0; border-bottom: 1px solid var(--border); }
              .policy-block:first-child{ padding-top:0; }
              .policy-block:last-child{ border-bottom:none; padding-bottom:0; }
              .policy-block h2{
                font-family: var(--font-body); font-weight:800; text-transform:none; letter-spacing:normal;
                font-size:21px; color: var(--gold); margin-bottom:14px; scroll-margin-top:100px;
              }
              .policy-block p{ font-size:15px; line-height:1.75; color: var(--text-muted); }
            }
            """;

}
