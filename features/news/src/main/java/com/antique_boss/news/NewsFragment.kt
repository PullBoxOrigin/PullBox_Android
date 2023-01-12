package com.antique_boss.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.antique_boss.news.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var hotPostAdapter: HotPostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
    }

    private fun initialize() {
        setupHotPostRecyclerView()
    }

    private fun setupHotPostRecyclerView() {
        val hotPosts = listOf<String>(
            "구글 번역(영어: Google Translate)은 텍스트 번역을 위해 구글이 무료로 제공하는 다언어 기계 번역 서비스이다. 안드로이드, iOS용 웹사이트 인터페이스, 모바일 앱, 그리고 개발자들이 브라우저 확장과 응용 소프트웨어를 개발하는 데 도움을 주는 API를 제공한다. 다른 언어로 번역되는 대상은 문자의 단락, 문서나 웹페이지이다. ",
            "대한민국에서는 \"구글 번역기\"라는 용어도 이따금씩 사용되지만 구글이 밝히는 정식 용어는 \"구글 번역\"이다. 구글 번역은 100개가 넘는 언어를 다양한 수준에서 지원하며 2017년 5월 기준으로 날마다 500,000,000 (5억)명 이상의 요청을 처리한다.",
            "통계 기계 번역 서비스로 2006년 4월부터 시작되었으며 언어 데이터를 모으기 위해 사용해 유엔과 유럽 의회의 구술 기록을 사용했다. 언어를 직접 번역하지 않고 먼저 텍스트를 영어로 번역한 다음 대상 언어로 변환하는 식이었다. 번역 중에는 최고의 번역을 결정할 수 있도록 수백 건의 문서 중 패턴들을 찾아본다. ",
            "정확도는 수차례 비판을 받았다. 2016년 11월, 구글은 구글 번역이 신경 기계 언어 엔진인 구글 신경 기계 번역(GNMT)으로 전환한다고 발표하였으며, 이는 조각 단위가 아닌 한 번에 전체 문장을 번역해준다. 가장 적절한 번역을 찾아내기 위해 더 넓은 문맥을 사용하며 그 다음 재정렬하여 적절한 문법으로 인간이 구사하는 것과 같은 내용으로 맞춘다. 처음에는 2016년에 일부 언어에만 활성화되었다가 GNMT는 점차 더 많은 언어를 위해 사용이되고 있다",
            "Babel Fish와 AOL, Yahoo!, MSN 등 내부적으로 시스트란를 사용하는 번역 서비스와 달리 구글은 독자적인 번역 엔진을 사용하고 있다. 구글 번역 엔진, 기계 번역 중에서도 통계적 기계 번역이라는 기술을 이용하고 있다. 이 방법은 the United Nations Documents를 데이터로 닦아왔다. 번역된 데이터는 이백억 정도의 단어로 구성된다. 원래는 방법과 이를 목표 언어로 번역한 (유엔 번역사의 손에 의한 것)것을 사용하여 패턴을 찾아 그 때 번역에 필요한 전문가 시스템을 만드는 것이다. 차기 버전은 현재 아랍어 - 영어 등의 번역에 관해서는, 오픈 베타 테스트 단계에 있다",
            "이 서비스는 또한 웹페이지를 번역할 수 있다. 번역할 수 있는 단락의 수(줄바꿈 태그 \"<br>\"등으로 표시)는 페이지 단위로 제한되어 있지만, 웹페이지의 텍스트가 \"<br>\"없이 분할되어 있는 경우는 수천 어로 이루어진 장대한 웹페이지도 번역할 수 있다.",
            "대화 모드는 음성 인식 기능을 통해서, 서로 다른 언어를 구사하는 두 사람 간의 대화를 문자 메시지 형식으로 실시간으로 번역해주는 기능이다. 번역된 문장을 읽어주기도 한다."
        )
        hotPostAdapter = HotPostAdapter(hotPosts)
        binding.hotPostRecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.hotPostRecyclerView.adapter = hotPostAdapter
    }
}