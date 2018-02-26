<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册协议</title>
    <link rel="stylesheet" href="../css/reset.css">
    <style>
        .agreement{
            width: 1310px;
            height: auto;
            margin: 0 auto;
            padding: 50px 25px;
            box-sizing: border-box;
            color: #333;
        }
        .title{
            width: 100%;
            height:auto;
        }
        h2{
            width: 100%;
            height: 40px;
            line-height: 40px;
            text-align: center;
            font-size: 24px;
        }
        .sub-title{
            width: 100%;
            height: auto;
            line-height: 24px;
            font-size: 14px;

        }
        .chapter{
            width: 100%;
            height: auto;
        }
        .chapter h2{
            font-size: 20px;
        }
        .chapter-title{
            font-size: 16px;
            line-height: 36px;
            font-weight: bold;
        }
        .paragraph{
            font-size: 12px;
            line-height: 20px;
        }
    </style>
</head>
<body>
<div class="agreement">
    <div class="title">
        <h2>用户注册协议</h2>
        <p class="sub-title">本《用户注册协议》（以下简称“本协议”）是您和北京森凝科技有限公司（以下简称“森凝”）就注册、使用附随本协议的CFer训练馆管理平台及森凝将不时开发的其他产品）（以下简称“系统”/“本系统”）于北京市朝阳区订立的具有法律约束力的协议。</p>
    </div>
    <div class="chapter">
        <h2> 总则</h2>
        <p class="chapter-title">第一章	总则</p>
        <p class="paragraph">CFer训练馆管理系统是指由森凝自主开发并享有知识产权、所有权等全部合法权益的软件系统。本协议项下您可使用的系统类型以您实际向森凝购买或通过森凝公布的官方渠道下载或以其他合法方式获取的特定软件系统为准。您（指依据中国法律具有完全民事行为能力的自然人或者设立并有效存续的企业法人或其他组织形式，以下简称“您”）通过运行、使用本系统，并在注册页面或系统显示的其他页面挑选“接受上述条款并继续”或类似按钮，这表示您与森凝达成并接受本协议。<u><b>在您选择注册账号并使用本系统之前，请您务必仔细阅读以下条款，特别是以加粗和下划线形式提示您注意的条款。若您一旦挑选“接受上述条款并继续”或类似按钮，则表示您同意受以下全部条款的约束。若您不接受以下全部或部分条款，请您立即停止使用本系统。</b></u></p>

        <p class="chapter-title">第二章	系统功能</p>
        <p class="paragraph"><b>第一条</b>	森凝提供的系统包含以下业务板块及功能，<u><b>您在此同意，下述系统类型及功能可能因森凝的单方判断而被增加或修改，或因定期、不定期的维护而暂缓提供</b></u>：</p>
        <p class="paragraph">（1)	健身房业务管理功能，您可以通过CFer训练馆管理平台进行课程安排及会员课程在线预约、会员管理、数据报表的制作与管理、运营推广等操作，并通过CFer训练馆平台的健身教练端进行自主查看每日课程、代会员约课、课程排期、会员管理等多种操作。</p>
        <p class="paragraph">（2)	森凝不断开发、补充的新业务板块和功能。</p>
        <p class="paragraph"><b>第二条</b>	森凝保留在任何时候自行决定对系统及其相关功能变更、升级、修改、转移的权利。森凝进一步保留开发新的模块、功能和软件或其它语种服务的权利。上述所有新的模块、功能、软件及服务的提供，除非森凝另有说明，否则仍适用本协议。</p>
        <p class="paragraph"><b>第三条</b>	森凝提供的各项业务板块和功能均有其相应的操作规则，您需按照具体的页面提示或规则进行操作和使用，包括但不限于提交信息、进行验证、交纳相关费用等；否则，您可能无法使用全部或部分系统功能。<u><b>您在此同意并确认，森凝在任何情况下都无需向您在使用本系统时发生的在传输或联络中的迟延、不准确、错误或疏漏及因此而致使的损害负责。</b></u></p>

        <p class="chapter-title">第三章	系统使用规则</p>
        <p class="paragraph"><b>第四条</b>	系统的获取：您应当从森凝公布的官方渠道下载或以其他合法方式获取本系统。如果您从未经森凝授权的第三方获取本系统或与本系统名称相同的安装程序，森凝无法保证该安装后的系统能够正常使用，并对因此给您造成的损失不予负责。</p>
        <p class="paragraph"><b>第五条</b>	注册账号：您应当按照森凝显示的注册页面提示进行账号注册的相关操作并提供注册所需的信息、资料。您应妥善保管注册账号和密码，同时对注册账号和密码安全承担全部责任。<u><b>除有充足的相反证据外，通过您的注册账号和密码进行的全部操作均视为您自行操作的行为。</b></u></p>
        <p class="paragraph"><b>第六条</b>	系统升级功能：基于改进服务、提高用户体验的目的，森凝有权不定时对系统进行更新；同时，您可以通过森凝公布的官方渠道下载更新过的软件系统版本。</p>

        <p class="chapter-title">第四章	您的权利和义务</p>
        <p class="paragraph"><b>第七条</b>	您应当保证向森凝提交的注册信息均真实、准确、及时、详尽和完整，并根据实际情况变化更新注册资料，以符合前述要求；您保证您提交的信息不含有任何违反国家法律、法规及中华人民共和国承认或加入的国际条约的内容，且您通过本系统所从事的一切活动都是合法、真实、有效的，不侵犯任何第三方的合法权益。</p>
        <p class="paragraph"><b>第八条</b>	因黑客攻击、木马、病毒等行为或您的保管疏忽等导致注册账号、密码遭他人非法使用所造成的损失由您自行承担，森凝不承担任何责任。</p>
        <p class="paragraph"><b>第九条</b>	您理解并保证不利用本系统传输或发表包含以下内容的言论：<br />
            （1)	反对宪法所确定的基本原则，煽动、抗拒、破坏宪法和法律、行政法规实施的；<br />
            （2)	煽动颠覆国家政权，推翻社会主义制度，煽动、分裂国家，破坏国家统一的；<br />
            （3)	损害国家机关信誉的、损害国家荣誉和利益的、损害社会公共利益和涉及国家安全的；<br />
            （4)	煽动民族仇恨、民族歧视，破坏民族团结的；<br />
            （5)	对种族、性别、宗教、地域等歧视的；<br />
            （6)	捏造或者歪曲事实，散布谣言，扰乱社会秩序的；<br />
            （7)	宣扬封建迷信、邪教、淫秽、色情、赌博、暴力、凶杀、恐怖、教唆犯罪的；<br />
            （8)	骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的、公然侮辱他人或者捏造事实诽谤他人的，或者进行其他恶意攻击的；<br />
            （9)	其他违反宪法和法律行政法规的。</p>
        <p class="paragraph"><b>第十条</b>	您在此声明并保证：<br />
            （1)	您通过本系统发布的所有信息（包括但不限于您使用的企业名称、Logo、商品信息、商品介绍等），均系真实、完整、合法的信息，不侵犯任何第三方的知识产权、所有权、肖像权、名誉权、姓名权、商誉等合法权益，但森凝及其关联公司无义务对此等信息进行审核。<br />
            （2)	您通过本系统所发布的任何信息或提供的任何资料都必须符合森凝当时及此后公示的发布须知及相关文字说明。<br />
            （3)	您将通过本系统维护并及时更新上述资料，以保证其真实、准确、及时、合法和完整性</p>
        <p class="paragraph"><b>第十一条	<u>您进一步理解并同意，如果您提供的信息不真实，不准确，不及时，不合法或不完全，或森凝有合理的理由怀疑其真实性，准确性、及时性、合法性和完整性，则森凝有权拒绝您使用本系统及以后可能提供的系统或服务。</u></b></p>
        <p class="paragraph"><b>第十二条	<u>为保证森凝的合法权益不受侵害，以下是森凝禁止您进行的一些有害活动的示例，包括但不限于：</u></b><br />（1)	禁止出售、转售或复制、开发森凝授予的使用权限；<br />
            （2)	禁止基于商业目的模仿森凝的产品和服务；<br />
            （3)	禁止复制和模仿森凝的设计理念、界面、功能和图表；<br />
            （4)	禁止未经森凝许可基于系统或其内容进行修改或制造派生其他产品；<br />
            （5)	您只能处于您商业范围内使用本系统，禁止发送违反法律的信息，禁止发送和储存带有病毒的、蠕虫的、木马的和其他有害的计算机代码、文件、脚本和程序。</p>
        <p class="paragraph"><b>第十三条	<u>您进一步同意对本系统和/或本系统任何部分（包括但不限于系统部件、页面标识、服务品牌、资讯、信息等）不进行复制、翻译、修改、适应、增强、反编译、反汇编、反向工程、分解拆卸、出售、转租或作任何商业目的的使用。您同意约束其有必要使用本系统的员工、代理、咨询者或顾问遵守前述之义务，并就其违反前述规定的行为对森凝承担如同您自身违反所产生的责任。</u></b></p>
        <p class="paragraph"><b>第十四条</b>	您必须自行配备上网所需的设备，包括计算机、调制解调器或其它必备的上网设备，并自行负担上网所需的电话费用、网络服务费用等。</p>
        <p class="paragraph"><b>第十五条</b>	您承诺通过本系统进行的活动所引发的一切法律后果，由您承担全部责任。如因您使用本系统的行为，导致森凝或任何第三方为此承担了相关的责任，则您需全额赔偿森凝或任何第三方的相关支出及损失，包括合理的律师费用。</p>
        <p class="paragraph"><b>第十六条	<u>您同意如因您违反本协议规定的任何条款或森凝不时发布的各项规则、通告，森凝有权单方判断立即终止您使用本系统，而无需事先通知您。</u></b></p>
        <p class="paragraph"><b>第十七条</b>	您同意在使用本系统的同时，同意接受森凝提供的各类信息服务。</p>

        <p class="chapter-title">第五章	森凝的权利和义务</p>
        <p class="paragraph"><b>第十八条</b>	森凝将按照本协议的规定许可您使用本系统，并有权按照森凝公布的其他规则或者与您签署的其他协议约定向您收取一定的服务费、管理费或其他费用。</p>
        <p class="paragraph"><b>第十九条</b>	森凝有权随时删除含有任何违反法律法规、本协议及森凝发布的各项规则的信息或链接，包括森凝对此有合理怀疑的信息。森凝有权单独对您提供的信息是否属于上述范围做出判断。</p>
        <p class="paragraph"><b>第二十条	<u>如果森凝发现或收到他人举报您发布的信息或提供的服务违反相关法律规定或侵犯任何第三方的合法权益的，森凝有权进行独立判断并采取技术手段予以删除、屏蔽或断开链接。同时，森凝有权视您的行为性质，采取包括但不限于暂停或终止服务，限制、冻结或终止您使用本系统的权利，追究您的法律责任等措施；同时，森凝在采取上述措施之后将以适当的方式（包括但不限于邮件、电话、公告等）通知您。您违反本协议约定或您与其他第三方的约定，导致任何第三方损害的，您应当独立承担责任；森凝因此遭受损失的，您也应当一并赔偿。</u></b></p>

        <p class="chapter-title">第六章	信息使用规则</p>
        <p class="paragraph"><b>第二十一条	<u>您在此同意，在您使用本系统的过程中，森凝将会按照本协议规定的目的、方式和范围收集和使用与您的相关信息，包括但不限于姓名／名称、地址、联系方式、简介、课程安排及教练安排、健身房场馆相关信息、交易记录及交易流水等。在使用本系统前，请您仔细阅读以下条款。本章中以下条款的规定将取代森凝与您在本协议签署之前达成的其他协议或约定（如有）。</u></b></p>
        <p class="paragraph"><b>第二十二条</b>	一般情况下，您可根据系统页面的提示或规则浏览、访问、修改、删除自己提交的信息。</p>
        <p class="paragraph"><b>第二十三条	<u>您理解并同意，森凝会基于以下目的收集和使用您的相关信息：</u></b><br />
            （1)	为您提供与系统使用相关的服务。<br />
            （2)	帮助我们设计新服务，提升现有系统体验。<br />
            （3)	在我们提供服务时，用于身份验证、客户服务、安全防范、诈骗监测、存档和备份用途等，确保我们向您提供的系统和服务的安全性。<br />
            （4)	向您发送您可能感兴趣的产品和服务的信息；向您提供与您更加相关的定向广告以替代普遍投放的广告；邀请您参与森凝的活动和市场调查。<br />
            （5)	向您推荐、提供潜在的商业合作伙伴及商业合作机会，促进您商业活动的开展。<br />
            （6)	开展内部审计、数据分析和研究，改善我们的产品、服务及与用户之间的沟通。</p>
        <p class="paragraph"><b>第二十四条	<u>森凝不会在未经您同意或授权的情况下将您的相关信息提供给第三方。但是，您在此同意，森凝可在以下情况下共享或披露您的相关信息：</u></b><br/>
            （1)	在获取同意的情况下共享：获得您的同意后，森凝会与其他方共享您的相关信息。<br/>
            （2)	共享给森凝的关联公司：在本协议的使用目的范围内，您的相关信息可能会与森凝的关联公司共享。作为一项政策，我们只会共享必要的信息。<br/>
            （3)	共享给授权合作伙伴：森凝可能会与合作伙伴共享您的某些相关信息，以向您提供更好的客户服务和用户体验，或者向您推荐、提供潜在的商业合作伙伴及商业合作机会等目的。我们仅会共享提供服务所必要的信息。<br/>
            （4)	基于法律或合理依据的披露：在法律、法律程序、诉讼或公共和政府主管部门有要求的情况下，森凝可能会披露您的相关信息。在某些管辖区，如果森凝牵涉到重组、合并或破产和清理诉讼，那么您的相关信息还会披露给交易方。森凝还会在存在合理需求的情况下披露您的信息，例如出于执行条款与条件以及保护客户的目的。</p>
        <p class="paragraph"><b>第二十五条</b>	森凝将在其网络系统内建立合理的安全体系，包括身份识别体系、内部安全防范体系，以使您数据保持完整及使用本系统的安全性。但您了解并同意技术手段在不断更新，森凝无法杜绝全部的非安全因素，就因技术水平限制等非因森凝原因产生的数据问题，森凝不承担责任。但森凝会及时更新安全体系，妥善维护网络及相关数据。</p>

        <p class="chapter-title">第七章	第三方服务说明</p>
        <p class="paragraph"><b>第二十六条</b>	您了解并确认本协议中可能包含由第三方提供的服务，森凝只是为了您的便利而提供该功能模块，但是森凝并不控制第三方的服务内容，也不对其负责。您如需使用此等服务，应另行与第三方服务提供方达成服务协议，支付相应费用并承担可能的风险。森凝对第三方提供的服务不提供任何形式的保证。</p>

        <p class="chapter-title">第八章	知识产权</p>
        <p class="paragraph"><b>第二十七条</b>	森凝拥有本协议所规定的系统的著作权、商标权、专利权、专利申请权、专有技术、商业秘密以及其他相关的知识产权，包括与该系统有关的各种文档资料。其它本协议中未经提及的权利亦由森凝保留。</p>
        <p class="paragraph"><b>第二十八条	<u>未经森凝事先书面同意，您不得为任何营利性或非营利性的目的自行实施、利用、转让或许可任何第三方实施、利用、转让上述知识产权。</u></b></p>

        <p class="chapter-title">第九章	协议的终止</p>
        <p class="paragraph"><b>第二十九条	<u>森凝有权在下列情形下拒绝您的订购或终止本协议，而无需承担任何责任：</u></b><br />
            （1)	您违反了本协议的任一条款，且自森凝通知您纠正后的合理期限内仍未纠正的；<br />
            （2)	森凝或森凝授权的第三方无法确认您提供的注册信息的真实性的；<br />
            （3)	森凝授予您的系统使用许可期限届满。系统使用许可期限届满后如需继续使用本协议约定之系统的，您需与森凝另行签署有关购买该系统的标准订单或按照森凝公布的规则获取系统使用权，并且可能需要您向森凝或森凝指定的第三方支付相应的费用。</p>
        <p class="paragraph"><b>第三十条</b>	如协议一方，除为重组或兼并的目的外，通过决议或由法院判令解散，则接收方或指定的债权人代表有权经通知后终止本协议。</p>

        <p class="chapter-title">第十章	免责及责任的限制与排除</p>
        <p class="paragraph"><b>第三十一条</b>	森凝以“现状”、“有缺陷”和“当前功能”的状态提供本系统。森凝不保证系统在操作上不会中断或没有错误，不保证其会纠正系统的所有缺陷，亦不保证系统能满足您的所有要求。您承担所有关于令人满意的质量、性能、准确性的风险。</p>
        <p class="paragraph"><b>第三十二条	<u>在所适用的法律允许的范围内，森凝不做任何明示的或默示的声明，也不给予任何明示的或默示的保证或条件，包括但不限于：</u></b><br />
            （1)	关于适销性、特定用途适用性、准确性和无侵权行为的任何保证或条件；<br />
            （2)	在交易过程或行业惯例中产生的任何保证或条件；<br />
            （3)	在访问或使用系统时不受干扰、没有错误的任何保证或条件。</p>
        <p class="paragraph"><b>第三十三条	<u>除森凝存在故意或重大过失或者森凝与您有其他约定外，森凝不就因您使用本系统引起的，或在任何方面与森凝的产品和服务有关的任何意外的、非直接的、特殊的、或间接的损害或请求（包括但不限于因人身伤害、因隐私泄漏、因未能履行包括诚信或合理谨慎在内的任何义务、因过失和因任何其他金钱上的损失或其他损失而造成的损害赔偿）承担任何责任。</u></b></p>
        <p class="paragraph"><b>第三十四条</b>	森凝不就电讯系统或互联网的中断或无法运作、技术故障、计算机错误或病毒、信息损坏或丢失或其它在森凝合理控制范围之外的原因而产生的其他任何性质的破坏而向您或任何第三方承担损害赔偿责任。</p>

        <p class="chapter-title">第十一章	不可抗力</p>
        <p class="paragraph"><b>第三十五条</b>	对于黑客攻击或政府管制或网络通讯瘫痪等对其发生和后果不能预见的事件，双方均确认此属不可抗力；双方应按照不可抗力对影响履行本协议的程度，协商决定是否解除本协议、免除履行本协议的部分义务，或者延期履行本协议。</p>

        <p class="chapter-title">第十二章	违约责任</p>
        <p class="paragraph"><b>第三十六条</b>	您同意保障和维护森凝的利益，负责支付由您违反本协议和/或其他服务条款引起的律师费用、损害补偿费用、政府机关处罚费用和其它侵权赔偿费用等。如您违反本协议中的任一条款，森凝保留以合法手段追究您的违约或侵权责任的权利。</p>

        <p class="chapter-title">第十三章	法律及争议解决</p>
        <p class="paragraph"><b>第三十七条</b>	本协议的订立、效力、履行、解释和争议的解除均适用中华人民共和国法律。</p>
        <p class="paragraph"><b>第三十八条	<u>因双方就本协议的签订、履行或解释发生争议，双方应努力友好协商解决。如协商不成，任何一方均有权向森凝住所地有管辖权的人民法院起诉。</u></b></p>

        <p class="chapter-title">第十四章	其他</p>
        <p class="paragraph"><b>第三十九条</b>     双方承认本协议反映了双方就协商谈判达成的一致意见。双方均完全了解本条款的后果并进一步承认本协议的合理性。</p>
        <p class="paragraph"><b>第四十条</b>	如本协议的任何条款被视作无效或无法执行，则上述条款可被分离，其余部分则仍具有法律效力。</p>
        <p class="paragraph"><b>第四十一条</b>	本协议的标题仅为方便阅读所设，非对条款的定义、限制、解释或描述其范围或界限。</p>
        <p class="paragraph"><b>第四十二条</b>	森凝于您过失或违约时放弃本协议规定的权利的，不得视为其对您的其他或以后同类之过失或违约行为弃权。</p>
        <p class="paragraph"><b>第四十三条	<u>森凝有权随时根据中华人民共和国有关法律、法规的变化、互联网的发展以及公司经营状况和经营策略的调整等修改本协议。您若继续使用本系统就有必要对最新的《用户注册协议》进行仔细阅读和确认。您若不同意更新后的协议，请您立即停止使用本系统；如果您继续使用本系统，则视为您接受森凝对本协议相关条款所做的修改。当发生有关争议时，以最新的协议为准。</u></b></p>
    </div>

</div>
</body>
</html>