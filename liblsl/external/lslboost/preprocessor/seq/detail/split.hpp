# /* **************************************************************************
#  *                                                                          *
#  *     (C) Copyright Paul Mensonides 2002.
#  *     Distributed under the Boost Software License, Version 1.0. (See
#  *     accompanying file LICENSE_1_0.txt or copy at
#  *     http://www.lslboost.org/LICENSE_1_0.txt)
#  *                                                                          *
#  ************************************************************************** */
#
# /* See http://www.lslboost.org for most recent version. */
#
# ifndef BOOST_PREPROCESSOR_SEQ_DETAIL_SPLIT_HPP
# define BOOST_PREPROCESSOR_SEQ_DETAIL_SPLIT_HPP
#
# include <lslboost/preprocessor/config/config.hpp>
#
# /* BOOST_PP_SEQ_SPLIT */
#
# define BOOST_PP_SEQ_SPLIT(n, seq) BOOST_PP_SEQ_SPLIT_D(n, seq)
#
# if ~BOOST_PP_CONFIG_FLAGS() & BOOST_PP_CONFIG_MWCC()
#    define BOOST_PP_SEQ_SPLIT_D(n, seq) (BOOST_PP_SEQ_SPLIT_ ## n seq)
# else
#    define BOOST_PP_SEQ_SPLIT_D(n, seq) (BOOST_PP_SEQ_SPLIT_ ## n ## seq)
# endif
#
# define BOOST_PP_SEQ_SPLIT_1(x) (x),
# define BOOST_PP_SEQ_SPLIT_2(x) (x) BOOST_PP_SEQ_SPLIT_1
# define BOOST_PP_SEQ_SPLIT_3(x) (x) BOOST_PP_SEQ_SPLIT_2
# define BOOST_PP_SEQ_SPLIT_4(x) (x) BOOST_PP_SEQ_SPLIT_3
# define BOOST_PP_SEQ_SPLIT_5(x) (x) BOOST_PP_SEQ_SPLIT_4
# define BOOST_PP_SEQ_SPLIT_6(x) (x) BOOST_PP_SEQ_SPLIT_5
# define BOOST_PP_SEQ_SPLIT_7(x) (x) BOOST_PP_SEQ_SPLIT_6
# define BOOST_PP_SEQ_SPLIT_8(x) (x) BOOST_PP_SEQ_SPLIT_7
# define BOOST_PP_SEQ_SPLIT_9(x) (x) BOOST_PP_SEQ_SPLIT_8
# define BOOST_PP_SEQ_SPLIT_10(x) (x) BOOST_PP_SEQ_SPLIT_9
# define BOOST_PP_SEQ_SPLIT_11(x) (x) BOOST_PP_SEQ_SPLIT_10
# define BOOST_PP_SEQ_SPLIT_12(x) (x) BOOST_PP_SEQ_SPLIT_11
# define BOOST_PP_SEQ_SPLIT_13(x) (x) BOOST_PP_SEQ_SPLIT_12
# define BOOST_PP_SEQ_SPLIT_14(x) (x) BOOST_PP_SEQ_SPLIT_13
# define BOOST_PP_SEQ_SPLIT_15(x) (x) BOOST_PP_SEQ_SPLIT_14
# define BOOST_PP_SEQ_SPLIT_16(x) (x) BOOST_PP_SEQ_SPLIT_15
# define BOOST_PP_SEQ_SPLIT_17(x) (x) BOOST_PP_SEQ_SPLIT_16
# define BOOST_PP_SEQ_SPLIT_18(x) (x) BOOST_PP_SEQ_SPLIT_17
# define BOOST_PP_SEQ_SPLIT_19(x) (x) BOOST_PP_SEQ_SPLIT_18
# define BOOST_PP_SEQ_SPLIT_20(x) (x) BOOST_PP_SEQ_SPLIT_19
# define BOOST_PP_SEQ_SPLIT_21(x) (x) BOOST_PP_SEQ_SPLIT_20
# define BOOST_PP_SEQ_SPLIT_22(x) (x) BOOST_PP_SEQ_SPLIT_21
# define BOOST_PP_SEQ_SPLIT_23(x) (x) BOOST_PP_SEQ_SPLIT_22
# define BOOST_PP_SEQ_SPLIT_24(x) (x) BOOST_PP_SEQ_SPLIT_23
# define BOOST_PP_SEQ_SPLIT_25(x) (x) BOOST_PP_SEQ_SPLIT_24
# define BOOST_PP_SEQ_SPLIT_26(x) (x) BOOST_PP_SEQ_SPLIT_25
# define BOOST_PP_SEQ_SPLIT_27(x) (x) BOOST_PP_SEQ_SPLIT_26
# define BOOST_PP_SEQ_SPLIT_28(x) (x) BOOST_PP_SEQ_SPLIT_27
# define BOOST_PP_SEQ_SPLIT_29(x) (x) BOOST_PP_SEQ_SPLIT_28
# define BOOST_PP_SEQ_SPLIT_30(x) (x) BOOST_PP_SEQ_SPLIT_29
# define BOOST_PP_SEQ_SPLIT_31(x) (x) BOOST_PP_SEQ_SPLIT_30
# define BOOST_PP_SEQ_SPLIT_32(x) (x) BOOST_PP_SEQ_SPLIT_31
# define BOOST_PP_SEQ_SPLIT_33(x) (x) BOOST_PP_SEQ_SPLIT_32
# define BOOST_PP_SEQ_SPLIT_34(x) (x) BOOST_PP_SEQ_SPLIT_33
# define BOOST_PP_SEQ_SPLIT_35(x) (x) BOOST_PP_SEQ_SPLIT_34
# define BOOST_PP_SEQ_SPLIT_36(x) (x) BOOST_PP_SEQ_SPLIT_35
# define BOOST_PP_SEQ_SPLIT_37(x) (x) BOOST_PP_SEQ_SPLIT_36
# define BOOST_PP_SEQ_SPLIT_38(x) (x) BOOST_PP_SEQ_SPLIT_37
# define BOOST_PP_SEQ_SPLIT_39(x) (x) BOOST_PP_SEQ_SPLIT_38
# define BOOST_PP_SEQ_SPLIT_40(x) (x) BOOST_PP_SEQ_SPLIT_39
# define BOOST_PP_SEQ_SPLIT_41(x) (x) BOOST_PP_SEQ_SPLIT_40
# define BOOST_PP_SEQ_SPLIT_42(x) (x) BOOST_PP_SEQ_SPLIT_41
# define BOOST_PP_SEQ_SPLIT_43(x) (x) BOOST_PP_SEQ_SPLIT_42
# define BOOST_PP_SEQ_SPLIT_44(x) (x) BOOST_PP_SEQ_SPLIT_43
# define BOOST_PP_SEQ_SPLIT_45(x) (x) BOOST_PP_SEQ_SPLIT_44
# define BOOST_PP_SEQ_SPLIT_46(x) (x) BOOST_PP_SEQ_SPLIT_45
# define BOOST_PP_SEQ_SPLIT_47(x) (x) BOOST_PP_SEQ_SPLIT_46
# define BOOST_PP_SEQ_SPLIT_48(x) (x) BOOST_PP_SEQ_SPLIT_47
# define BOOST_PP_SEQ_SPLIT_49(x) (x) BOOST_PP_SEQ_SPLIT_48
# define BOOST_PP_SEQ_SPLIT_50(x) (x) BOOST_PP_SEQ_SPLIT_49
# define BOOST_PP_SEQ_SPLIT_51(x) (x) BOOST_PP_SEQ_SPLIT_50
# define BOOST_PP_SEQ_SPLIT_52(x) (x) BOOST_PP_SEQ_SPLIT_51
# define BOOST_PP_SEQ_SPLIT_53(x) (x) BOOST_PP_SEQ_SPLIT_52
# define BOOST_PP_SEQ_SPLIT_54(x) (x) BOOST_PP_SEQ_SPLIT_53
# define BOOST_PP_SEQ_SPLIT_55(x) (x) BOOST_PP_SEQ_SPLIT_54
# define BOOST_PP_SEQ_SPLIT_56(x) (x) BOOST_PP_SEQ_SPLIT_55
# define BOOST_PP_SEQ_SPLIT_57(x) (x) BOOST_PP_SEQ_SPLIT_56
# define BOOST_PP_SEQ_SPLIT_58(x) (x) BOOST_PP_SEQ_SPLIT_57
# define BOOST_PP_SEQ_SPLIT_59(x) (x) BOOST_PP_SEQ_SPLIT_58
# define BOOST_PP_SEQ_SPLIT_60(x) (x) BOOST_PP_SEQ_SPLIT_59
# define BOOST_PP_SEQ_SPLIT_61(x) (x) BOOST_PP_SEQ_SPLIT_60
# define BOOST_PP_SEQ_SPLIT_62(x) (x) BOOST_PP_SEQ_SPLIT_61
# define BOOST_PP_SEQ_SPLIT_63(x) (x) BOOST_PP_SEQ_SPLIT_62
# define BOOST_PP_SEQ_SPLIT_64(x) (x) BOOST_PP_SEQ_SPLIT_63
# define BOOST_PP_SEQ_SPLIT_65(x) (x) BOOST_PP_SEQ_SPLIT_64
# define BOOST_PP_SEQ_SPLIT_66(x) (x) BOOST_PP_SEQ_SPLIT_65
# define BOOST_PP_SEQ_SPLIT_67(x) (x) BOOST_PP_SEQ_SPLIT_66
# define BOOST_PP_SEQ_SPLIT_68(x) (x) BOOST_PP_SEQ_SPLIT_67
# define BOOST_PP_SEQ_SPLIT_69(x) (x) BOOST_PP_SEQ_SPLIT_68
# define BOOST_PP_SEQ_SPLIT_70(x) (x) BOOST_PP_SEQ_SPLIT_69
# define BOOST_PP_SEQ_SPLIT_71(x) (x) BOOST_PP_SEQ_SPLIT_70
# define BOOST_PP_SEQ_SPLIT_72(x) (x) BOOST_PP_SEQ_SPLIT_71
# define BOOST_PP_SEQ_SPLIT_73(x) (x) BOOST_PP_SEQ_SPLIT_72
# define BOOST_PP_SEQ_SPLIT_74(x) (x) BOOST_PP_SEQ_SPLIT_73
# define BOOST_PP_SEQ_SPLIT_75(x) (x) BOOST_PP_SEQ_SPLIT_74
# define BOOST_PP_SEQ_SPLIT_76(x) (x) BOOST_PP_SEQ_SPLIT_75
# define BOOST_PP_SEQ_SPLIT_77(x) (x) BOOST_PP_SEQ_SPLIT_76
# define BOOST_PP_SEQ_SPLIT_78(x) (x) BOOST_PP_SEQ_SPLIT_77
# define BOOST_PP_SEQ_SPLIT_79(x) (x) BOOST_PP_SEQ_SPLIT_78
# define BOOST_PP_SEQ_SPLIT_80(x) (x) BOOST_PP_SEQ_SPLIT_79
# define BOOST_PP_SEQ_SPLIT_81(x) (x) BOOST_PP_SEQ_SPLIT_80
# define BOOST_PP_SEQ_SPLIT_82(x) (x) BOOST_PP_SEQ_SPLIT_81
# define BOOST_PP_SEQ_SPLIT_83(x) (x) BOOST_PP_SEQ_SPLIT_82
# define BOOST_PP_SEQ_SPLIT_84(x) (x) BOOST_PP_SEQ_SPLIT_83
# define BOOST_PP_SEQ_SPLIT_85(x) (x) BOOST_PP_SEQ_SPLIT_84
# define BOOST_PP_SEQ_SPLIT_86(x) (x) BOOST_PP_SEQ_SPLIT_85
# define BOOST_PP_SEQ_SPLIT_87(x) (x) BOOST_PP_SEQ_SPLIT_86
# define BOOST_PP_SEQ_SPLIT_88(x) (x) BOOST_PP_SEQ_SPLIT_87
# define BOOST_PP_SEQ_SPLIT_89(x) (x) BOOST_PP_SEQ_SPLIT_88
# define BOOST_PP_SEQ_SPLIT_90(x) (x) BOOST_PP_SEQ_SPLIT_89
# define BOOST_PP_SEQ_SPLIT_91(x) (x) BOOST_PP_SEQ_SPLIT_90
# define BOOST_PP_SEQ_SPLIT_92(x) (x) BOOST_PP_SEQ_SPLIT_91
# define BOOST_PP_SEQ_SPLIT_93(x) (x) BOOST_PP_SEQ_SPLIT_92
# define BOOST_PP_SEQ_SPLIT_94(x) (x) BOOST_PP_SEQ_SPLIT_93
# define BOOST_PP_SEQ_SPLIT_95(x) (x) BOOST_PP_SEQ_SPLIT_94
# define BOOST_PP_SEQ_SPLIT_96(x) (x) BOOST_PP_SEQ_SPLIT_95
# define BOOST_PP_SEQ_SPLIT_97(x) (x) BOOST_PP_SEQ_SPLIT_96
# define BOOST_PP_SEQ_SPLIT_98(x) (x) BOOST_PP_SEQ_SPLIT_97
# define BOOST_PP_SEQ_SPLIT_99(x) (x) BOOST_PP_SEQ_SPLIT_98
# define BOOST_PP_SEQ_SPLIT_100(x) (x) BOOST_PP_SEQ_SPLIT_99
# define BOOST_PP_SEQ_SPLIT_101(x) (x) BOOST_PP_SEQ_SPLIT_100
# define BOOST_PP_SEQ_SPLIT_102(x) (x) BOOST_PP_SEQ_SPLIT_101
# define BOOST_PP_SEQ_SPLIT_103(x) (x) BOOST_PP_SEQ_SPLIT_102
# define BOOST_PP_SEQ_SPLIT_104(x) (x) BOOST_PP_SEQ_SPLIT_103
# define BOOST_PP_SEQ_SPLIT_105(x) (x) BOOST_PP_SEQ_SPLIT_104
# define BOOST_PP_SEQ_SPLIT_106(x) (x) BOOST_PP_SEQ_SPLIT_105
# define BOOST_PP_SEQ_SPLIT_107(x) (x) BOOST_PP_SEQ_SPLIT_106
# define BOOST_PP_SEQ_SPLIT_108(x) (x) BOOST_PP_SEQ_SPLIT_107
# define BOOST_PP_SEQ_SPLIT_109(x) (x) BOOST_PP_SEQ_SPLIT_108
# define BOOST_PP_SEQ_SPLIT_110(x) (x) BOOST_PP_SEQ_SPLIT_109
# define BOOST_PP_SEQ_SPLIT_111(x) (x) BOOST_PP_SEQ_SPLIT_110
# define BOOST_PP_SEQ_SPLIT_112(x) (x) BOOST_PP_SEQ_SPLIT_111
# define BOOST_PP_SEQ_SPLIT_113(x) (x) BOOST_PP_SEQ_SPLIT_112
# define BOOST_PP_SEQ_SPLIT_114(x) (x) BOOST_PP_SEQ_SPLIT_113
# define BOOST_PP_SEQ_SPLIT_115(x) (x) BOOST_PP_SEQ_SPLIT_114
# define BOOST_PP_SEQ_SPLIT_116(x) (x) BOOST_PP_SEQ_SPLIT_115
# define BOOST_PP_SEQ_SPLIT_117(x) (x) BOOST_PP_SEQ_SPLIT_116
# define BOOST_PP_SEQ_SPLIT_118(x) (x) BOOST_PP_SEQ_SPLIT_117
# define BOOST_PP_SEQ_SPLIT_119(x) (x) BOOST_PP_SEQ_SPLIT_118
# define BOOST_PP_SEQ_SPLIT_120(x) (x) BOOST_PP_SEQ_SPLIT_119
# define BOOST_PP_SEQ_SPLIT_121(x) (x) BOOST_PP_SEQ_SPLIT_120
# define BOOST_PP_SEQ_SPLIT_122(x) (x) BOOST_PP_SEQ_SPLIT_121
# define BOOST_PP_SEQ_SPLIT_123(x) (x) BOOST_PP_SEQ_SPLIT_122
# define BOOST_PP_SEQ_SPLIT_124(x) (x) BOOST_PP_SEQ_SPLIT_123
# define BOOST_PP_SEQ_SPLIT_125(x) (x) BOOST_PP_SEQ_SPLIT_124
# define BOOST_PP_SEQ_SPLIT_126(x) (x) BOOST_PP_SEQ_SPLIT_125
# define BOOST_PP_SEQ_SPLIT_127(x) (x) BOOST_PP_SEQ_SPLIT_126
# define BOOST_PP_SEQ_SPLIT_128(x) (x) BOOST_PP_SEQ_SPLIT_127
# define BOOST_PP_SEQ_SPLIT_129(x) (x) BOOST_PP_SEQ_SPLIT_128
# define BOOST_PP_SEQ_SPLIT_130(x) (x) BOOST_PP_SEQ_SPLIT_129
# define BOOST_PP_SEQ_SPLIT_131(x) (x) BOOST_PP_SEQ_SPLIT_130
# define BOOST_PP_SEQ_SPLIT_132(x) (x) BOOST_PP_SEQ_SPLIT_131
# define BOOST_PP_SEQ_SPLIT_133(x) (x) BOOST_PP_SEQ_SPLIT_132
# define BOOST_PP_SEQ_SPLIT_134(x) (x) BOOST_PP_SEQ_SPLIT_133
# define BOOST_PP_SEQ_SPLIT_135(x) (x) BOOST_PP_SEQ_SPLIT_134
# define BOOST_PP_SEQ_SPLIT_136(x) (x) BOOST_PP_SEQ_SPLIT_135
# define BOOST_PP_SEQ_SPLIT_137(x) (x) BOOST_PP_SEQ_SPLIT_136
# define BOOST_PP_SEQ_SPLIT_138(x) (x) BOOST_PP_SEQ_SPLIT_137
# define BOOST_PP_SEQ_SPLIT_139(x) (x) BOOST_PP_SEQ_SPLIT_138
# define BOOST_PP_SEQ_SPLIT_140(x) (x) BOOST_PP_SEQ_SPLIT_139
# define BOOST_PP_SEQ_SPLIT_141(x) (x) BOOST_PP_SEQ_SPLIT_140
# define BOOST_PP_SEQ_SPLIT_142(x) (x) BOOST_PP_SEQ_SPLIT_141
# define BOOST_PP_SEQ_SPLIT_143(x) (x) BOOST_PP_SEQ_SPLIT_142
# define BOOST_PP_SEQ_SPLIT_144(x) (x) BOOST_PP_SEQ_SPLIT_143
# define BOOST_PP_SEQ_SPLIT_145(x) (x) BOOST_PP_SEQ_SPLIT_144
# define BOOST_PP_SEQ_SPLIT_146(x) (x) BOOST_PP_SEQ_SPLIT_145
# define BOOST_PP_SEQ_SPLIT_147(x) (x) BOOST_PP_SEQ_SPLIT_146
# define BOOST_PP_SEQ_SPLIT_148(x) (x) BOOST_PP_SEQ_SPLIT_147
# define BOOST_PP_SEQ_SPLIT_149(x) (x) BOOST_PP_SEQ_SPLIT_148
# define BOOST_PP_SEQ_SPLIT_150(x) (x) BOOST_PP_SEQ_SPLIT_149
# define BOOST_PP_SEQ_SPLIT_151(x) (x) BOOST_PP_SEQ_SPLIT_150
# define BOOST_PP_SEQ_SPLIT_152(x) (x) BOOST_PP_SEQ_SPLIT_151
# define BOOST_PP_SEQ_SPLIT_153(x) (x) BOOST_PP_SEQ_SPLIT_152
# define BOOST_PP_SEQ_SPLIT_154(x) (x) BOOST_PP_SEQ_SPLIT_153
# define BOOST_PP_SEQ_SPLIT_155(x) (x) BOOST_PP_SEQ_SPLIT_154
# define BOOST_PP_SEQ_SPLIT_156(x) (x) BOOST_PP_SEQ_SPLIT_155
# define BOOST_PP_SEQ_SPLIT_157(x) (x) BOOST_PP_SEQ_SPLIT_156
# define BOOST_PP_SEQ_SPLIT_158(x) (x) BOOST_PP_SEQ_SPLIT_157
# define BOOST_PP_SEQ_SPLIT_159(x) (x) BOOST_PP_SEQ_SPLIT_158
# define BOOST_PP_SEQ_SPLIT_160(x) (x) BOOST_PP_SEQ_SPLIT_159
# define BOOST_PP_SEQ_SPLIT_161(x) (x) BOOST_PP_SEQ_SPLIT_160
# define BOOST_PP_SEQ_SPLIT_162(x) (x) BOOST_PP_SEQ_SPLIT_161
# define BOOST_PP_SEQ_SPLIT_163(x) (x) BOOST_PP_SEQ_SPLIT_162
# define BOOST_PP_SEQ_SPLIT_164(x) (x) BOOST_PP_SEQ_SPLIT_163
# define BOOST_PP_SEQ_SPLIT_165(x) (x) BOOST_PP_SEQ_SPLIT_164
# define BOOST_PP_SEQ_SPLIT_166(x) (x) BOOST_PP_SEQ_SPLIT_165
# define BOOST_PP_SEQ_SPLIT_167(x) (x) BOOST_PP_SEQ_SPLIT_166
# define BOOST_PP_SEQ_SPLIT_168(x) (x) BOOST_PP_SEQ_SPLIT_167
# define BOOST_PP_SEQ_SPLIT_169(x) (x) BOOST_PP_SEQ_SPLIT_168
# define BOOST_PP_SEQ_SPLIT_170(x) (x) BOOST_PP_SEQ_SPLIT_169
# define BOOST_PP_SEQ_SPLIT_171(x) (x) BOOST_PP_SEQ_SPLIT_170
# define BOOST_PP_SEQ_SPLIT_172(x) (x) BOOST_PP_SEQ_SPLIT_171
# define BOOST_PP_SEQ_SPLIT_173(x) (x) BOOST_PP_SEQ_SPLIT_172
# define BOOST_PP_SEQ_SPLIT_174(x) (x) BOOST_PP_SEQ_SPLIT_173
# define BOOST_PP_SEQ_SPLIT_175(x) (x) BOOST_PP_SEQ_SPLIT_174
# define BOOST_PP_SEQ_SPLIT_176(x) (x) BOOST_PP_SEQ_SPLIT_175
# define BOOST_PP_SEQ_SPLIT_177(x) (x) BOOST_PP_SEQ_SPLIT_176
# define BOOST_PP_SEQ_SPLIT_178(x) (x) BOOST_PP_SEQ_SPLIT_177
# define BOOST_PP_SEQ_SPLIT_179(x) (x) BOOST_PP_SEQ_SPLIT_178
# define BOOST_PP_SEQ_SPLIT_180(x) (x) BOOST_PP_SEQ_SPLIT_179
# define BOOST_PP_SEQ_SPLIT_181(x) (x) BOOST_PP_SEQ_SPLIT_180
# define BOOST_PP_SEQ_SPLIT_182(x) (x) BOOST_PP_SEQ_SPLIT_181
# define BOOST_PP_SEQ_SPLIT_183(x) (x) BOOST_PP_SEQ_SPLIT_182
# define BOOST_PP_SEQ_SPLIT_184(x) (x) BOOST_PP_SEQ_SPLIT_183
# define BOOST_PP_SEQ_SPLIT_185(x) (x) BOOST_PP_SEQ_SPLIT_184
# define BOOST_PP_SEQ_SPLIT_186(x) (x) BOOST_PP_SEQ_SPLIT_185
# define BOOST_PP_SEQ_SPLIT_187(x) (x) BOOST_PP_SEQ_SPLIT_186
# define BOOST_PP_SEQ_SPLIT_188(x) (x) BOOST_PP_SEQ_SPLIT_187
# define BOOST_PP_SEQ_SPLIT_189(x) (x) BOOST_PP_SEQ_SPLIT_188
# define BOOST_PP_SEQ_SPLIT_190(x) (x) BOOST_PP_SEQ_SPLIT_189
# define BOOST_PP_SEQ_SPLIT_191(x) (x) BOOST_PP_SEQ_SPLIT_190
# define BOOST_PP_SEQ_SPLIT_192(x) (x) BOOST_PP_SEQ_SPLIT_191
# define BOOST_PP_SEQ_SPLIT_193(x) (x) BOOST_PP_SEQ_SPLIT_192
# define BOOST_PP_SEQ_SPLIT_194(x) (x) BOOST_PP_SEQ_SPLIT_193
# define BOOST_PP_SEQ_SPLIT_195(x) (x) BOOST_PP_SEQ_SPLIT_194
# define BOOST_PP_SEQ_SPLIT_196(x) (x) BOOST_PP_SEQ_SPLIT_195
# define BOOST_PP_SEQ_SPLIT_197(x) (x) BOOST_PP_SEQ_SPLIT_196
# define BOOST_PP_SEQ_SPLIT_198(x) (x) BOOST_PP_SEQ_SPLIT_197
# define BOOST_PP_SEQ_SPLIT_199(x) (x) BOOST_PP_SEQ_SPLIT_198
# define BOOST_PP_SEQ_SPLIT_200(x) (x) BOOST_PP_SEQ_SPLIT_199
# define BOOST_PP_SEQ_SPLIT_201(x) (x) BOOST_PP_SEQ_SPLIT_200
# define BOOST_PP_SEQ_SPLIT_202(x) (x) BOOST_PP_SEQ_SPLIT_201
# define BOOST_PP_SEQ_SPLIT_203(x) (x) BOOST_PP_SEQ_SPLIT_202
# define BOOST_PP_SEQ_SPLIT_204(x) (x) BOOST_PP_SEQ_SPLIT_203
# define BOOST_PP_SEQ_SPLIT_205(x) (x) BOOST_PP_SEQ_SPLIT_204
# define BOOST_PP_SEQ_SPLIT_206(x) (x) BOOST_PP_SEQ_SPLIT_205
# define BOOST_PP_SEQ_SPLIT_207(x) (x) BOOST_PP_SEQ_SPLIT_206
# define BOOST_PP_SEQ_SPLIT_208(x) (x) BOOST_PP_SEQ_SPLIT_207
# define BOOST_PP_SEQ_SPLIT_209(x) (x) BOOST_PP_SEQ_SPLIT_208
# define BOOST_PP_SEQ_SPLIT_210(x) (x) BOOST_PP_SEQ_SPLIT_209
# define BOOST_PP_SEQ_SPLIT_211(x) (x) BOOST_PP_SEQ_SPLIT_210
# define BOOST_PP_SEQ_SPLIT_212(x) (x) BOOST_PP_SEQ_SPLIT_211
# define BOOST_PP_SEQ_SPLIT_213(x) (x) BOOST_PP_SEQ_SPLIT_212
# define BOOST_PP_SEQ_SPLIT_214(x) (x) BOOST_PP_SEQ_SPLIT_213
# define BOOST_PP_SEQ_SPLIT_215(x) (x) BOOST_PP_SEQ_SPLIT_214
# define BOOST_PP_SEQ_SPLIT_216(x) (x) BOOST_PP_SEQ_SPLIT_215
# define BOOST_PP_SEQ_SPLIT_217(x) (x) BOOST_PP_SEQ_SPLIT_216
# define BOOST_PP_SEQ_SPLIT_218(x) (x) BOOST_PP_SEQ_SPLIT_217
# define BOOST_PP_SEQ_SPLIT_219(x) (x) BOOST_PP_SEQ_SPLIT_218
# define BOOST_PP_SEQ_SPLIT_220(x) (x) BOOST_PP_SEQ_SPLIT_219
# define BOOST_PP_SEQ_SPLIT_221(x) (x) BOOST_PP_SEQ_SPLIT_220
# define BOOST_PP_SEQ_SPLIT_222(x) (x) BOOST_PP_SEQ_SPLIT_221
# define BOOST_PP_SEQ_SPLIT_223(x) (x) BOOST_PP_SEQ_SPLIT_222
# define BOOST_PP_SEQ_SPLIT_224(x) (x) BOOST_PP_SEQ_SPLIT_223
# define BOOST_PP_SEQ_SPLIT_225(x) (x) BOOST_PP_SEQ_SPLIT_224
# define BOOST_PP_SEQ_SPLIT_226(x) (x) BOOST_PP_SEQ_SPLIT_225
# define BOOST_PP_SEQ_SPLIT_227(x) (x) BOOST_PP_SEQ_SPLIT_226
# define BOOST_PP_SEQ_SPLIT_228(x) (x) BOOST_PP_SEQ_SPLIT_227
# define BOOST_PP_SEQ_SPLIT_229(x) (x) BOOST_PP_SEQ_SPLIT_228
# define BOOST_PP_SEQ_SPLIT_230(x) (x) BOOST_PP_SEQ_SPLIT_229
# define BOOST_PP_SEQ_SPLIT_231(x) (x) BOOST_PP_SEQ_SPLIT_230
# define BOOST_PP_SEQ_SPLIT_232(x) (x) BOOST_PP_SEQ_SPLIT_231
# define BOOST_PP_SEQ_SPLIT_233(x) (x) BOOST_PP_SEQ_SPLIT_232
# define BOOST_PP_SEQ_SPLIT_234(x) (x) BOOST_PP_SEQ_SPLIT_233
# define BOOST_PP_SEQ_SPLIT_235(x) (x) BOOST_PP_SEQ_SPLIT_234
# define BOOST_PP_SEQ_SPLIT_236(x) (x) BOOST_PP_SEQ_SPLIT_235
# define BOOST_PP_SEQ_SPLIT_237(x) (x) BOOST_PP_SEQ_SPLIT_236
# define BOOST_PP_SEQ_SPLIT_238(x) (x) BOOST_PP_SEQ_SPLIT_237
# define BOOST_PP_SEQ_SPLIT_239(x) (x) BOOST_PP_SEQ_SPLIT_238
# define BOOST_PP_SEQ_SPLIT_240(x) (x) BOOST_PP_SEQ_SPLIT_239
# define BOOST_PP_SEQ_SPLIT_241(x) (x) BOOST_PP_SEQ_SPLIT_240
# define BOOST_PP_SEQ_SPLIT_242(x) (x) BOOST_PP_SEQ_SPLIT_241
# define BOOST_PP_SEQ_SPLIT_243(x) (x) BOOST_PP_SEQ_SPLIT_242
# define BOOST_PP_SEQ_SPLIT_244(x) (x) BOOST_PP_SEQ_SPLIT_243
# define BOOST_PP_SEQ_SPLIT_245(x) (x) BOOST_PP_SEQ_SPLIT_244
# define BOOST_PP_SEQ_SPLIT_246(x) (x) BOOST_PP_SEQ_SPLIT_245
# define BOOST_PP_SEQ_SPLIT_247(x) (x) BOOST_PP_SEQ_SPLIT_246
# define BOOST_PP_SEQ_SPLIT_248(x) (x) BOOST_PP_SEQ_SPLIT_247
# define BOOST_PP_SEQ_SPLIT_249(x) (x) BOOST_PP_SEQ_SPLIT_248
# define BOOST_PP_SEQ_SPLIT_250(x) (x) BOOST_PP_SEQ_SPLIT_249
# define BOOST_PP_SEQ_SPLIT_251(x) (x) BOOST_PP_SEQ_SPLIT_250
# define BOOST_PP_SEQ_SPLIT_252(x) (x) BOOST_PP_SEQ_SPLIT_251
# define BOOST_PP_SEQ_SPLIT_253(x) (x) BOOST_PP_SEQ_SPLIT_252
# define BOOST_PP_SEQ_SPLIT_254(x) (x) BOOST_PP_SEQ_SPLIT_253
# define BOOST_PP_SEQ_SPLIT_255(x) (x) BOOST_PP_SEQ_SPLIT_254
# define BOOST_PP_SEQ_SPLIT_256(x) (x) BOOST_PP_SEQ_SPLIT_255
#
# endif
